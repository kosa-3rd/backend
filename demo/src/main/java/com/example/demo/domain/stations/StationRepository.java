package com.example.demo.domain.stations;

import com.example.demo.interfaces.controller.station.dto.StationInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    @Query("select new com.example.demo.interfaces.controller.station.dto.StationInfoDTO(s.id, s.name) from Station s where s.subway.id = :subwayId")
    List<StationInfoDTO> getStationsBySubwayId(@Param("subwayId") long subwayId);

}
