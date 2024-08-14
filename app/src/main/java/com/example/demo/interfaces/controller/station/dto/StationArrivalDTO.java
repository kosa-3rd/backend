package com.example.demo.interfaces.controller.station.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class StationArrivalDTO {
    private long subwayId;
    private String subwayName;
    private List<TrainArrivalDTO> trainArrivals;

    public StationArrivalDTO(long subwayId, String subwayName) {
        this.subwayId = subwayId;
        this.subwayName = subwayName;
        trainArrivals = new ArrayList<>();
    }
}
