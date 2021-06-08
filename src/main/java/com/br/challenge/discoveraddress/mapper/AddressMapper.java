package com.br.challenge.discoveraddress.mapper;

import com.br.challenge.discoveraddress.controller.resource.AddressCepResource;
import com.br.challenge.discoveraddress.controller.resource.AddressResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mappings({
            @Mapping(target="rua", source="logradouro"),
            @Mapping(target="cidade", source="localidade"),
            @Mapping(target="estado", source="uf")
    })
    AddressResponse addressCepResourceToAddressResponse(AddressCepResource addressCepResource);
}
