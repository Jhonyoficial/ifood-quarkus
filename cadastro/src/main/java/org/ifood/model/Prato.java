package org.ifood.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "prato")
public class Prato extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prato")
    public Long idPrato;

    @Column(name = "ds_nome_prato")
    public String dsNomePrato;

    @Column(name = "descricao")
    public String descricao;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    public Restaurante restaurante;

    @Column(name = "nm_valor")
    public BigDecimal nmValor;
}
