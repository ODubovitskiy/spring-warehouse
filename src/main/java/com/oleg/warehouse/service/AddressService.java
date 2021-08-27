package com.oleg.warehouse.service;

import com.oleg.warehouse.dto.AddressDTO;
import com.oleg.warehouse.entity.AddressEntity;
import com.oleg.warehouse.exception.AddressAlreadyExistsEsception;
import com.oleg.warehouse.exception.AddressNotFoundEException;
import com.oleg.warehouse.factory.AddressDTOFactory;
import com.oleg.warehouse.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    AddressRepository addressRepository;
    AddressDTOFactory addressDTOFactory;

    @Autowired
    public AddressService(AddressRepository addressRepository, AddressDTOFactory addressDTOFactory) {
        this.addressRepository = addressRepository;
        this.addressDTOFactory = addressDTOFactory;
    }

    public AddressDTO save(AddressEntity addressEntity) throws AddressAlreadyExistsEsception {
        if (addressRepository.existsAddressEntitiesByZipAndCountryAndCityAndStreetAndBuilding(
                addressEntity.getZip(),
                addressEntity.getCountry(),
                addressEntity.getCity(),
                addressEntity.getStreet(),
                addressEntity.getBuilding()))
            throw new AddressAlreadyExistsEsception("This address already exists");
        return addressDTOFactory.makeDefault(addressRepository.save(addressEntity));
    }

    public AddressDTO getById(Long id) throws AddressNotFoundEException {
        if (!addressRepository.existsById(id))
            throw new AddressNotFoundEException(String.format("Address with id=%s doesn't exist!", id));
        return addressDTOFactory.makeDefault(addressRepository.findById(id).get());
    }
}
