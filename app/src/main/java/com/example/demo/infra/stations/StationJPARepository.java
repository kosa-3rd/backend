package com.example.demo.infra.stations;

import com.example.demo.domain.stations.Station;
import com.example.demo.interfaces.controller.station.dto.StationArrivalDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StationJPARepository extends JpaRepository<Station, Long> {

    @Query("select new com.example.demo.interfaces.controller.station.dto.StationArrivalDTO(s.subway.id, s.subway.name)" +
            " from Station s where s.name = :station")
    List<StationArrivalDTO> getStationArrivalInfoByStationName(@Param("station") String station);

    @Query("select s.name from Station s where s.id = :id")
    Optional<String> getNameById(@Param("id") long id);
}
