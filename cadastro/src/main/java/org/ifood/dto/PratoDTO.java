package org.ifood.dto;

import org.ifood.model.Restaurante;

import java.math.BigDecimal;

public class PratoDTO {

    public Long idPrato;

    public String dsNomePrato;

    public String descricao;

    public BigDecimal nmValor;

    public RestauranteDTO restaurante;
}
