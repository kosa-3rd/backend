package com.example.demo.interfaces.controller.station;

import com.example.demo.domain.stations.StationService;
import com.example.demo.domain.stations.TrainInfo;
import com.example.demo.interfaces.controller.station.dto.StationDTO;
import com.example.demo.interfaces.controller.station.dto.StationInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/station")
@RestController
public class StationController {
    private StationService stationService;




    @GetMapping("/list")
    public ResponseEntity<List<StationInfoDTO>> getStationsBySubwayId(@RequestParam long subwayId) {
        return new ResponseEntity<List<StationInfoDTO>> (stationService.getStationsBySubwayId(subwayId), HttpStatus.OK);
    }
    @GetMapping("/{name}")
    public List<TrainInfo> getStationArrivalInfo(@PathVariable String name){
        return stationService.getData(name);
    }
}
