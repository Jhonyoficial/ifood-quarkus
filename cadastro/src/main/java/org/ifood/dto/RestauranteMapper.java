package org.ifood.dto;

import org.ifood.Restaurante;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {

    Restaurante toRestaurante(RestauranteDTO restauranteDTO);

}
