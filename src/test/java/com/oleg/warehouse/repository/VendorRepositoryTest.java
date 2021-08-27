package com.oleg.warehouse.repository;

import com.oleg.warehouse.entity.VendorEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class VendorRepositoryTest {

    final private VendorRepository vendorRepositoryUnderTest;

    @Autowired
    public VendorRepositoryTest(VendorRepository vendorRepositoryUnderTest) {
        this.vendorRepositoryUnderTest = vendorRepositoryUnderTest;
    }

    @BeforeEach
    void setUp() {
        seedVendorTable();
    }

    @AfterEach
    void tearDown() {
        vendorRepositoryUnderTest.deleteAll();
    }

    @Test
    void findByName() {
        //given
        //when
        VendorEntity cisco = vendorRepositoryUnderTest.findByName("Cisco");
        VendorEntity polycom = vendorRepositoryUnderTest.findByName("Polycom");

        //then
        assertThat(cisco.getName()).isEqualTo("Cisco");
        assertThat(polycom.getName()).isEqualTo("Polycom");
    }

    private void seedVendorTable() {
        vendorRepositoryUnderTest.save(new VendorEntity("Cisco"));
        vendorRepositoryUnderTest.save(new VendorEntity("Polycom"));
        vendorRepositoryUnderTest.save(new VendorEntity("Yealink"));
    }
}