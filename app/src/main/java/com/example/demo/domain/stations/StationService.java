package com.example.demo.domain.stations;

import com.example.demo.interfaces.controller.station.dto.StationDTO;
import com.example.demo.interfaces.controller.station.dto.StationInfoDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StationService {
    @Autowired
    private StationRepository stationRepository;

    @Value("${STATION_KEY}")
    private String stationKey;


    @Autowired
    private ObjectMapper objectMapper;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<StationInfoDTO> getStationsBySubwayId(long subwayId) {
        return stationRepository.getStationsBySubwayId(subwayId);
    }

    public List<TrainInfo> getData(String stationName){
        String uri = "http://swopenapi.seoul.go.kr/api/subway/{key}/json/realtimeStationArrival/1/6/";
        String response = restTemplate.getForObject(uri, String.class, stationKey);
        return getThreeStationsInfo(stationName);

    }
    public List<TrainInfo> processSubwayInfo(String jsonResponse) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonResponse);
        JsonNode realtimeArrivalList = root.path("realtimeStationArrival").path("row");

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

            threeStationInfo.addAll(processSubwayInfo(adjacentStations[0]));
            threeStationInfo.addAll(currentStationInfo);
            threeStationInfo.addAll(processSubwayInfo(adjacentStations[1]));

            return threeStationInfo;

        }
        catch (Exception e){
            throw new IllegalArgumentException("Invalid station name");
        }

    }


}
