package com.example.demo.domain.subways;

import com.example.demo.domain.stations.Station;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name="subways")
public class Subway {

    @Id
    private long id;

    @Getter
    private String name;

    @Getter
    private String color;

    @Getter
    @OneToMany(mappedBy = "subway", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Station> stations;

}
