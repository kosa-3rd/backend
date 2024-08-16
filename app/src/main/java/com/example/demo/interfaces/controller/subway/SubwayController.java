package com.example.demo.interfaces.controller.subway;

import com.example.demo.domain.subways.Subway;
import com.example.demo.domain.subways.SubwayRepository;
import com.example.demo.interfaces.controller.subway.dto.SubwayAllInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/subway")
@RestController
public class SubwayController {
    @Autowired
    private SubwayRepository subwayRepository;

    @GetMapping("/list")
    public ResponseEntity<List<SubwayAllInfo>> getSubways() {
        return new ResponseEntity<List<SubwayAllInfo>>(subwayRepository.getSubways(), HttpStatus.OK);
    }

    @GetMapping("/name/{subwayId}")
    public String getSubway(@PathVariable long subwayId) {
        return subwayRepository.getNameById(subwayId).isPresent() ? subwayRepository.getNameById(subwayId).get() : "";
    }

}
