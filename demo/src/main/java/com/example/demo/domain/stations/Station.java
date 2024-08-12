package com.example.demo.domain.stations;

import com.example.demo.domain.subways.Subway;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Entity
@Table(name = "stations")
public class Station {

    @Id
    @Getter
    private long id;

    @Getter
    private String name;

    @Getter
    @ManyToOne
    private Subway subway;
}
