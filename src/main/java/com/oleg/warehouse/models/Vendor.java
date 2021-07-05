package com.oleg.warehouse.models;

import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "vendor")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    @NonNull
    private String name;
    @Column(name = "inn")
    @NonNull
    private String INN;

}
