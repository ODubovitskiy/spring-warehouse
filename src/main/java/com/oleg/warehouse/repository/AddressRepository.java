package com.oleg.warehouse.repository;

import com.oleg.warehouse.entity.AddressEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    boolean existsAddressEntitiesByZipAndCountryAndCityAndStreetAndBuilding(@NonNull String zip, @NonNull String country, @NonNull String city, @NonNull String street, @NonNull String building);
}
