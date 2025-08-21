package org.ifood.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "localizacao")
public class Localizacao extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_localizacao")
    public Long idLocalizacao;

    @Column(name = "nm_latitude")
    public Double nmLatitude;

    @Column(name = "nm_longitude")
    public Double nmLongitude;
}
