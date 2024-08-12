package com.example.demo.domain.subways;

import com.example.demo.interfaces.controller.subway.dto.SubwayAllInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubwayRepository extends JpaRepository<Subway, Long> {

    @Query("select new com.example.demo.interfaces.controller.subway.dto.SubwayAllInfo(s.id, s.name, s.color) from Subway s")
    List<SubwayAllInfo> getSubways();

}
