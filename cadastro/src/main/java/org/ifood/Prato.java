package org.ifood;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;

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

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    public Restaurante restaurante;

    @Column(name = "nm_valor")
    public BigDecimal nmValor;
}
