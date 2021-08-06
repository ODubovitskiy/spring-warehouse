package com.oleg.warehouse.factory;

import com.oleg.warehouse.dto.VendorDTO;
import com.oleg.warehouse.entities.VendorEntity;
import org.springframework.stereotype.Component;

@Component
public class VendorDTOFactory {

    public VendorDTO makeDefault(VendorEntity entity) {
        return VendorDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
