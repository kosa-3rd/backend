package com.example.demo.infra.subways;

import com.example.demo.domain.subways.Subway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubwayJPARepository extends JpaRepository<Subway, Long> {


}
