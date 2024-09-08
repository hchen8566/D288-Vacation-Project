package com.example.demo.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.lang.model.element.NestingKind;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "customers")
@Setter
@Getter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "customer_id", nullable = false)
    //@JsonProperty("id")
    private Long id;

    @Column(name = "address", nullable = false)
    //@JsonProperty("address")
    private String address;

    @Column(name = "create_date", updatable = false)
    @CreationTimestamp
   // @JsonProperty("create_date")
    private Date create_date;

    @Column(name = "customer_last_name", nullable = false)
    //@JsonProperty("lastName")
    private String lastName;

    @Column(name = "customer_first_name", nullable = false)
    //@JsonProperty("firstName")
    private String firstName;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;

    @Column(name = "phone", nullable = false)
    //@JsonProperty("phone")
    private String phone;

    @Column(name = "postal_code", nullable = false)
    //i @JsonProperty("postal_code")
    private String postal_code;

    @ManyToOne
    @JoinColumn(name = "division_id", nullable = false)
    private Division division;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Cart> carts = new HashSet<>();

    public Customer(){}

    public Customer(String firstName, String lastName, String address, String phone, String postal_code){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.postal_code = postal_code;
    }

    public void add(Cart cart) {
        carts.add(cart);
    }
}
