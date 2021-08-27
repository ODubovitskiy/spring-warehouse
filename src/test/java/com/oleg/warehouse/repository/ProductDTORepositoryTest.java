package com.oleg.warehouse.repository;

import com.oleg.warehouse.entity.ProductEntity;
import com.oleg.warehouse.entity.VendorEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ProductDTORepositoryTest {

    ProductRepository productRepositoryUnderTest;
    VendorRepository vendorRepositoryUnderTest;

    @Autowired
    public ProductDTORepositoryTest(ProductRepository productRepository, VendorRepository vendorRepository) {
        this.productRepositoryUnderTest = productRepository;
        this.vendorRepositoryUnderTest = vendorRepository;
    }

    @BeforeEach
    void setUp() {
        seedVendorTable();
    }

    @AfterEach
    void tearDown() {
        vendorRepositoryUnderTest.deleteAll();
        productRepositoryUnderTest.deleteAll();
    }

    @Test
    void ifReturnListOfProductsBySerialNumber() {

        //given
//        final VendorEntity CISCO = vendorRepositoryUnderTest.findByName("Cisco");
//        final VendorEntity POLYCOM = vendorRepositoryUnderTest.findByName("Polycom");
//        final VendorEntity YEALINK = vendorRepositoryUnderTest.findByName("Yealink");
//        final String SERIAL_NUMBER = "111";
//        ProductEntity firstProduct = new ProductEntity("First Product", SERIAL_NUMBER, 1000, "Sold", CISCO);
//        ProductEntity secondProduct = new ProductEntity("Second Product", SERIAL_NUMBER, 1000, "Sold", POLYCOM);
//        ProductEntity thirdProduct = new ProductEntity("Third Product", "222", 1000, "Sold", YEALINK);
//        productRepositoryUnderTest.save(firstProduct);
//        productRepositoryUnderTest.save(secondProduct);
//        productRepositoryUnderTest.save(thirdProduct);
//
//        //when
//        List<ProductEntity> listBySN = productRepositoryUnderTest.findBySerialNumber(SERIAL_NUMBER);
//
//        //then
//        assertThat(listBySN.size()).isEqualTo(2);
//        assertThat(listBySN.get(0)).isEqualTo(firstProduct);
//        assertThat(listBySN.get(1)).isEqualTo(secondProduct);
    }

    @Test
    void findByVendor() {
//
//        //given
//        final VendorEntity CISCO = vendorRepositoryUnderTest.findByName("Cisco");
//        final VendorEntity POLYCOM = vendorRepositoryUnderTest.findByName("Polycom");
//        ProductEntity firstProduct = new ProductEntity("First Product", "123", 1000, "Sold", CISCO);
//        ProductEntity secondProduct = new ProductEntity("Second Product", "321", 1000, "Sold", POLYCOM);
//        ProductEntity thirdProduct = new ProductEntity("Third Product", "222", 1000, "Sold", CISCO);
//        productRepositoryUnderTest.save(firstProduct);
//        productRepositoryUnderTest.save(secondProduct);
//        productRepositoryUnderTest.save(thirdProduct);

        //when
//        List<ProductEntity> listProductsByVendor = productRepositoryUnderTest.findByVendor(CISCO);
//
//        then
//        assertThat(listProductsByVendor.size()).isEqualTo(2);
    }

    @Test
    @Disabled
    void findAllByOrderByIdAsc() {
    }

    private void seedVendorTable() {
        vendorRepositoryUnderTest.save(new VendorEntity("Cisco"));
        vendorRepositoryUnderTest.save(new VendorEntity("Polycom"));
        vendorRepositoryUnderTest.save(new VendorEntity("Yealink"));
    }
}