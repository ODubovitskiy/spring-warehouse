package com.oleg.warehouse.factory;

import com.oleg.warehouse.dto.VendorDTO;
import com.oleg.warehouse.entity.VendorEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VendorDTOFactory {

    public VendorDTO makeDefault(VendorEntity entity) {
        return VendorDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public List<VendorDTO> makeVendorDTOList(List<VendorEntity> entities) {
        return entities
                .stream()
                .map(this::makeDefault)
                .collect(Collectors.toList());
    }
}
