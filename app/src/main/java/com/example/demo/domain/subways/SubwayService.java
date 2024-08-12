package com.example.demo.domain.subways;

import com.example.demo.interfaces.controller.subway.dto.SubwayAllInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubwayService {
    @Autowired
    private SubwayRepository subwayRepository;

    public List<SubwayAllInfo> getSubways() {
        return subwayRepository.getSubways();
    }

}
