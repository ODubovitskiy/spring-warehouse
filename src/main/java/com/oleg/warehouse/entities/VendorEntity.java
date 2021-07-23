package com.oleg.warehouse.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vendor")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class VendorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    @NonNull
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendor")
    @JsonBackReference
//    @JsonManagedReference

    private List<ProductEntity> products;
}
