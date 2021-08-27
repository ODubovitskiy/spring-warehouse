package com.oleg.warehouse.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class ProductEntity {

    @NonNull
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "vendor_id")
    private VendorEntity vendor;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @NonNull
    @Column(name = "description")
    private String description;
    @NonNull
    @Column(name = "serial_number")
    private String serialNumber;
    @NonNull
    @Column(name = "price")
    private Integer price;
    @NonNull
    @Column(name = "status")
    private String status;

}
