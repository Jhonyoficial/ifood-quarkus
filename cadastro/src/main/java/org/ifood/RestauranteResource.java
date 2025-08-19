package org.ifood;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Path("restaurante")
public class RestauranteResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Restaurante> listarTodosRestaurantes() {
        return Restaurante.listAll();
    }

    @POST
    @Transactional
    public void adicionarRestaurante(Restaurante restaurante) {
        restaurante.persist();
    }

    @PUT
    @Path("{id}")
    public void alterarRestaurante(@PathParam("id") Integer idRestaurante, Restaurante dto) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);

        if (restauranteOp.isEmpty()){
            throw new NotFoundException();
        }

        restauranteOp.get().dsNomeRestaurante = dto.dsNomeRestaurante;
        restauranteOp.get().persist();
    }

    @DELETE
    @Path("{id}")
    public void deletarRestaurante(@PathParam("id") Integer idRestaurante){
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        restauranteOp.ifPresentOrElse(Restaurante::delete, () -> {throw new NotFoundException();});
    }
}
