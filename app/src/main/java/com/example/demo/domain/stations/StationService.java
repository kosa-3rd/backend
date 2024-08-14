package com.example.demo.domain.stations;

import com.example.demo.domain.subways.SubwayRepository;
import com.example.demo.infra.stations.StationJPARepository;
import com.example.demo.interfaces.controller.station.dto.StationArrivalDTO;
import com.example.demo.interfaces.controller.station.dto.StationDTO;
import com.example.demo.interfaces.controller.station.dto.StationInfoDTO;
import com.example.demo.interfaces.controller.station.dto.TrainArrivalDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class StationService {
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private SubwayRepository subwayRepository;
    @Autowired
    private StationJPARepository stationJPARepository;

    @Value("${STATION_KEY}")
    private String stationKey;


    @Autowired
    private ObjectMapper objectMapper;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<StationInfoDTO> getStationsBySubwayId(long subwayId) {
        return stationRepository.getStationsBySubwayId(subwayId);
    }

    public List<TrainInfo> getData(String stationName){
        String uri = "http://swopenapi.seoul.go.kr/api/subway/{key}/json/realtimeStationArrival/1/6/" + stationName;
        String response = restTemplate.getForObject(uri, String.class, stationKey);
        return getThreeStationsInfo(response);
    }

    public List<TrainInfo> processSubwayInfo(String jsonResponse) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonResponse);
        JsonNode realtimeArrivalList = root.path("realtimeArrivalList");
        System.out.println("----" + realtimeArrivalList);

        List<TrainInfo> trainInfoList = new ArrayList<>();

        for (JsonNode trainData : realtimeArrivalList) {
            TrainInfo trainInfo = new TrainInfo(
                    trainData.path("subwayId").asText(),
                    trainData.path("updnLine").asText(),
                    trainData.path("trainLineNm").asText(),
                    trainData.path("statnNm").asText(),
                    trainData.path("barvlDt").asText(),
                    trainData.path("arvlMsg2").asText(),
                    trainData.path("arvlMsg3").asText()
            );
            trainInfoList.add(trainInfo);
        }

        return trainInfoList;
    }

    public String[] extractAdjacentStations(List<TrainInfo> currentStationInfo) {
        String prevStation = null;
        String nextStation = null;

        for (TrainInfo info : currentStationInfo) {
            String[] parts = info.trainLineNm().split("-");
            if (parts.length == 2) {
                if (info.updnLine().equals("0")) { // 상행
                    nextStation = parts[0].trim();
                } else if (info.updnLine().equals("1")) { // 하행
                    prevStation = parts[1].trim();
                }
            }
            if (prevStation != null && nextStation != null) {
                break;
            }
        }

        return new String[]{prevStation, nextStation};
    }
    public List<TrainInfo> getThreeStationsInfo(String currentStation)  {
        try{
            List<TrainInfo> currentStationInfo = processSubwayInfo(currentStation);
            String[] adjacentStations = extractAdjacentStations(currentStationInfo);

            List<TrainInfo> threeStationInfo = new ArrayList<>();
            System.out.println("-----" + currentStationInfo);
            System.out.println("adj : " + Arrays.toString(adjacentStations));

            threeStationInfo.addAll(processSubwayInfo(adjacentStations[0]));
            threeStationInfo.addAll(currentStationInfo);
            threeStationInfo.addAll(processSubwayInfo(adjacentStations[1]));

            return threeStationInfo;

        }
        catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("Invalid station name");
        }

    }

    public List<StationArrivalDTO> getStationArrivalInfo(String station) throws JsonProcessingException {
        List<StationArrivalDTO> stationArrivalDTOS = stationJPARepository.getStationArrivalInfoByStationName(station);

        String uri = "http://swopenapi.seoul.go.kr/api/subway/{key}/json/realtimeStationArrival/1/" + stationArrivalDTOS.size() * 4 + "/" + station;
        String response = restTemplate.getForObject(uri, String.class, stationKey);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);
        JsonNode realtimeArrivalList = root.path("realtimeArrivalList");

        for (JsonNode trainData : realtimeArrivalList) {

            System.out.println(trainData.path("statnTid").asLong());

            String trainTo = "▶" + stationJPARepository.getNameById(trainData.path("statnTid").asLong()).orElse("종점")
                    + "(" + trainData.path("trainLineNm").asText().split(" -")[0] + ")";
            String arvMsg = trainData.path("arvlMsg2").asText();
            int leftSeconds = Integer.parseInt(trainData.path("barvlDt").asText());

            if (leftSeconds > 0) {
                String[] rcvTime = trainData.path("recptnDt").asText().split(" ")[1].split(":");

                int min = (Integer.parseInt(rcvTime[1]) + ((Integer.parseInt(rcvTime[0]) + leftSeconds) / 60)) % 60;
                int hour = (Integer.parseInt(rcvTime[0]) + ((Integer.parseInt(rcvTime[1]) + ((Integer.parseInt(rcvTime[0]) + leftSeconds) / 60)) / 60)) % 24;

                arvMsg = String.format("%02d", hour) + ":" + String.format("%02d", min) + " 도착 예정";
            }

            TrainArrivalDTO trainArrivalDTO = new TrainArrivalDTO(
                    trainTo,
                    arvMsg
            );

            for (StationArrivalDTO dto: stationArrivalDTOS) {
                if (Long.parseLong(trainData.path("subwayId").asText()) == dto.getSubwayId()) {
                    dto.getTrainArrivals().add(trainArrivalDTO);
                }
            }
        }


        return stationArrivalDTOS;
    }


}
