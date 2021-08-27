package com.oleg.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vendor")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class VendorEntity {

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "vendor")
    @JsonBackReference
    private List<ProductEntity> products;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @Column(name = "name")
    @NonNull
    private String name;
}
