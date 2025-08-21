package org.ifood;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.ifood.dto.RestauranteDTO;
import org.ifood.dto.RestauranteMapper;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("restaurante")
public class RestauranteResource {

    @Inject
    RestauranteMapper restauranteMapper;

    @GET
    @Tag(name = "restaurante")
    public List<Restaurante> listarTodosRestaurantes() {
        return Restaurante.listAll();
    }

    @POST
    @Transactional
    @Tag(name = "restaurante")
    public Response adicionarRestaurante(RestauranteDTO dto) {
        Restaurante restaurante = restauranteMapper.toRestaurante(dto);
        restaurante.persist();
        return Response.ok(restaurante).build();
    }

    @PUT
    @Path("{id}")
    @Tag(name = "restaurante")
    public void alterarRestaurante(@PathParam("id") Integer idRestaurante, Restaurante dto) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);

        if (restauranteOp.isEmpty()) {
            throw new NotFoundException();
        }

        restauranteOp.get().dsNomeRestaurante = dto.dsNomeRestaurante;
        restauranteOp.get().persist();
    }

    @DELETE
    @Path("{id}")
    @Tag(name = "restaurante")
    public void deletarRestaurante(@PathParam("id") Integer idRestaurante) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        restauranteOp.ifPresentOrElse(Restaurante::delete, () -> {
            throw new NotFoundException();
        });
    }

    // Pratos

    @GET
    @Path("{idRestaurante}/pratos")
    @Tag(name = "prato")
    public List<Prato> buscarPratos(@PathParam("idRestaurante") Long idRestaurante) {
        Optional<Restaurante> restaurante = Restaurante.findByIdOptional(idRestaurante);

        if (restaurante.isEmpty()) {
            throw new NotFoundException("Restaurante não encontrado");
        }

        return Prato.list("restaurante", restaurante.get());
    }

    @POST
    @Path("{idRestaurante}/pratos")
    @Tag(name = "prato")
    public Response adicionarPrato(@PathParam("idRestaurante") Long idRestaurante, Prato dto) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        if (restauranteOp.isEmpty()) {
            throw new NotFoundException("Restaurante não encontrado");
        }

        Prato prato = new Prato();
        prato.dsNomePrato = dto.dsNomePrato;
        prato.descricao = dto.descricao;
        prato.nmValor = dto.nmValor;
        prato.restaurante = restauranteOp.get();
        prato.persist();

        return Response.ok(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{idRestaurante}/pratos/{idPrato}")
    @Tag(name = "prato")
    public void atualizarPrato(@PathParam("idRestaurante") Long idRestaurante, @PathParam("idPrato") Long idPrato, Prato dto) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        if (restauranteOp.isEmpty()) {
            throw new NotFoundException("Restaurante não encontrado");
        }

        Optional<Prato> pratoOp = Prato.findByIdOptional(idPrato);
        if (pratoOp.isEmpty()) {
            throw new NotFoundException("Prato não encontrato");
        }
        pratoOp.get().dsNomePrato = dto.dsNomePrato;
        pratoOp.get().nmValor = dto.nmValor;
        pratoOp.get().persist();
    }

    @DELETE
    @Path("{idRestaurante}/pratos/{idPrato}")
    @Tag(name = "prato")
    public void deletarPrato(@PathParam("idRestaurante")  Long idRestaurante, @PathParam("idPrato") Long idPrato) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        if (restauranteOp.isEmpty()) {
            throw new NotFoundException("Restaurante não encontrado");
        }

        Optional<Prato> pratoOp = Prato.findByIdOptional(idPrato);
        if (pratoOp.isEmpty()) {
            throw new NotFoundException("Prato não encontrato");
        }

        pratoOp.get().delete();
    }
}
