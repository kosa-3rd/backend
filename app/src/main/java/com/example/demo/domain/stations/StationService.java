package com.example.demo.domain.stations;

import com.example.demo.interfaces.controller.station.dto.StationInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StationService {
    @Autowired
    private StationRepository stationRepository;


    private final RestTemplate restTemplate = new RestTemplate();

    public List<StationInfoDTO> getStationsBySubwayId(long subwayId) {
        return stationRepository.getStationsBySubwayId(subwayId);
    }

    public String getData(String stationName){
        String uri = "http://swopenapi.seoul.go.kr/api/subway/715070786e726a7335327259436b49/json/realtimeStationArrival/1/6/";
        return restTemplate.getForObject(uri, String.class);
    }


}
