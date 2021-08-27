package com.oleg.warehouse.factory;

import com.oleg.warehouse.dto.AddressDTO;
import com.oleg.warehouse.entity.AddressEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressDTOFactory {

    public AddressDTO makeDefault(AddressEntity entity) {
        return AddressDTO.builder()
                .id(entity.getId())
                .zip(entity.getZip())
                .country(entity.getCountry())
                .city(entity.getCity())
                .street(entity.getStreet())
                .building(entity.getBuilding())
                .build();
    }

    public List<AddressDTO> makeAddressDTOList(List<AddressEntity> list) {
        return list.stream()
                .map(this::makeDefault)
                .collect(Collectors.toList());
    }
}
