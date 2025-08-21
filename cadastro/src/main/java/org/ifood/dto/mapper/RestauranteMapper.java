package org.ifood.dto.mapper;

import org.ifood.dto.RestauranteDTO;
import org.ifood.model.Restaurante;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {

    Restaurante toRestaurante(RestauranteDTO restauranteDTO);

    RestauranteDTO toRestauranteDTO(Restaurante restaurante);

    List<RestauranteDTO> toRestauranteDTO(List<Restaurante> restaurante);
}
