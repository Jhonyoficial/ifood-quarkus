package org.ifood.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDate;

public class RestauranteDTO {

    @JsonIgnore
    public Long idRestaurante;

    @Size(min = 3, max = 30)
    public String dsNomeRestaurante;

    @CNPJ
    @NotNull
    public String cnpj;

    public String dsNomeProprietario;

    @JsonIgnore
    public LocalDate dtCriacao;

    @JsonIgnore
    public LocalDate dtAtualizacao;

    public LocalizacaoDTO localizacao;

}
