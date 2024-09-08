package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vacations")
@Getter
@Setter
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_id", nullable = false)
    private Long id;

    @Column(name = "vacation_title", nullable = false)
    private String vacation_title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "travel_fare_price", nullable = false)
    private BigDecimal travel_price;

    @Column(name = "image_url", nullable = false)
    private String image_URL;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    private Date create_date;

    @UpdateTimestamp
    @Column(name = "last_update")
    private Date last_update;

    @OneToMany(mappedBy = "vacation_title", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Excursion> excursions = new HashSet<>();

    public Vacation(){}
}
