package com.example.demo.domain.subways;

import com.example.demo.interfaces.controller.subway.dto.SubwayAllInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubwayRepository extends JpaRepository<Subway, Long> {

    @Query("select new com.example.demo.interfaces.controller.subway.dto.SubwayAllInfo(s.id, s.name, s.color) from Subway s")
    List<SubwayAllInfo> getSubways();

    @Query("select s.name from Subway s where s.id = :id")
    Optional<String> getNameById(@Param("id") long id);

}
