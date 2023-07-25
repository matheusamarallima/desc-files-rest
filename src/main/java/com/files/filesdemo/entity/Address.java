package com.files.filesdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String neighborhood;
    private String city;
    private String postalCode;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Student student;

}
