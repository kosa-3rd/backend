package com.example.demo.domain.stations;

import com.example.demo.domain.subways.Subway;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Getter
@Entity
@Table(name = "stations")
public class Station {

    @Id
    private long id;

    private String name;

    @ManyToOne
    private Subway subway;
}
