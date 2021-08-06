package com.oleg.warehouse.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class ProductEntity {

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

    @NonNull
    @ManyToOne()
    @JoinColumn(name = "vendor_id")
//    @JsonManagedReference
//    @JsonBackReference

    private VendorEntity vendor;
}
