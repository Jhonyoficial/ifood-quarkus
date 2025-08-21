package org.ifood.dto.mapper;

import org.ifood.dto.PratoDTO;
import org.ifood.model.Prato;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface PratoMapper {

    PratoDTO toPratoDTO(Prato prato);

    List<PratoDTO> toPratoDTO(List<Prato> prato);

    Prato toPrato(Prato prato);

}
