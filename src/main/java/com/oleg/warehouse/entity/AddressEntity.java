package com.oleg.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class AddressEntity {


    @OneToMany(mappedBy = "address",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            orphanRemoval = true)
    @JsonBackReference
    List<ContractorEntity> contractors;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @Column(name = "zip")
    @NonNull
    private String zip;
    @Column(name = "country")
    @NonNull
    private String country;
    @Column(name = "city")
    @NonNull
    private String city;
    @Column(name = "street")
    @NonNull
    private String street;
    @Column(name = "building")
    @NonNull
    private String building;

}
