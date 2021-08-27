package com.oleg.warehouse.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@RequiredArgsConstructor
@Table(name = "contractor")
public class ContractorEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    AddressEntity address;

    @OneToMany(mappedBy = "contractor", cascade = CascadeType.ALL)
    @JsonBackReference
    List<WorkerEntity> workers;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "inn", unique = true)
    @NonNull
    private String inn;
}
