package com.example.demo.interfaces.controller.station.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class StationArrivalDTO {
    private String subwayName;
    private String stationName;
    private String prevStationName;
    private String nextStationName;
    private List<TrainArrivalDTO> trainArrivals;
}
