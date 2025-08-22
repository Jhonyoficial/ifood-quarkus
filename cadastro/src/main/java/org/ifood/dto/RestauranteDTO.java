package org.ifood.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class RestauranteDTO {

    @JsonIgnore
    public Long idRestaurante;

    public String dsNomeRestaurante;

    public String cnpj;

    public String dsNomeProprietario;

    @JsonIgnore
    public LocalDate dtCriacao;

    @JsonIgnore
    public LocalDate dtAtualizacao;

    public LocalizacaoDTO localizacao;

}
