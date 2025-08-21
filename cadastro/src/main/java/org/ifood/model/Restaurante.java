package org.ifood.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "restaurante")
public class Restaurante extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_restaurante")
    public Long idRestaurante;

    @Column(name = "ds_nome_restaurante")
    public String dsNomeRestaurante;

    @Column(name = "ds_cnpj")
    public String cnpj;

    @Column(name = "ds_nome_proprietario")
    public String dsNomeProprietario;

    @Column(name = "dt_criacao")
    public LocalDate dtCriacao;

    @Column(name = "dt_atualizacao")
    public LocalDate dtAtualizacao;

    @OneToOne(cascade = CascadeType.ALL)
    public Localizacao localizacao;
}
