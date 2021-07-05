package com.oleg.warehouse.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(name = "description")
    private String description;
    @NonNull
    @Column(name = "serial_number")
    private String serialNumber;
    @NonNull
    @Column(name = "vendor")
    private String vendor;
    @NonNull
    @Column(name = "price")
    private Integer price;
    @NonNull
    @Column(name = "status")
    private String status;

}
