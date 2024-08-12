package com.example.demo.domain.stations;

import com.example.demo.interfaces.controller.station.dto.StationInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    @Autowired
    private StationRepository stationRepository;

    public List<StationInfoDTO> getStationsBySubwayId(long subwayId) {
        return stationRepository.getStationsBySubwayId(subwayId);
    }
}
