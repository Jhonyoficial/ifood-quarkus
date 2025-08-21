package org.ifood.controller;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.ifood.dto.PratoDTO;
import org.ifood.dto.mapper.PratoMapper;
import org.ifood.model.Prato;
import org.ifood.model.Restaurante;
import org.ifood.dto.RestauranteDTO;
import org.ifood.dto.mapper.RestauranteMapper;

import java.util.List;
import java.util.Optional;
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("restaurante")
public class RestauranteResource {

    @Inject
    RestauranteMapper restauranteMapper;

    @Inject
    PratoMapper pratoMapper;

    @GET
    @Tag(name = "restaurante")
    public Response listarTodosRestaurantes() {
        List<Restaurante> listaRestaurante = Restaurante.listAll();
        List<RestauranteDTO> restauranteDTO = restauranteMapper.toRestauranteDTO(listaRestaurante);
        return Response.ok(restauranteDTO).build();
    }

    @POST
    @Transactional
    @Tag(name = "restaurante")
    public Response adicionarRestaurante(RestauranteDTO dto) {
        Restaurante restaurante = restauranteMapper.toRestaurante(dto);
        restaurante.persist();
        RestauranteDTO restauranteDTO = restauranteMapper.toRestauranteDTO(restaurante);
        return Response.ok(restauranteDTO).build();
    }

    @PUT
    @Path("{id}")
    @Tag(name = "restaurante")
    public Response alterarRestaurante(@PathParam("id") Integer idRestaurante, RestauranteDTO dto) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);

        if (restauranteOp.isEmpty()) {
            throw new NotFoundException();
        }

        restauranteOp.get().dsNomeRestaurante = dto.dsNomeRestaurante;
        restauranteOp.get().persist();

        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    @Tag(name = "restaurante")
    public Response deletarRestaurante(@PathParam("id") Integer idRestaurante) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        restauranteOp.ifPresentOrElse(Restaurante::delete, () -> {
            throw new NotFoundException();
        });

        return Response.ok().build();
    }

    // Pratos
    @GET
    @Path("{idRestaurante}/pratos")
    @Tag(name = "prato")
    public Response buscarPratos(@PathParam("idRestaurante") Long idRestaurante) {
        Optional<Restaurante> restaurante = Restaurante.findByIdOptional(idRestaurante);

        if (restaurante.isEmpty()) {
            throw new NotFoundException("Restaurante não encontrado");
        }

        List<Prato> listaPratos = Prato.list("restaurante", restaurante.get());
        List<PratoDTO> pratoDTO = pratoMapper.toPratoDTO(listaPratos);
        return Response.ok(pratoDTO).build();
    }

    @POST
    @Path("{idRestaurante}/pratos")
    @Tag(name = "prato")
    public Response adicionarPrato(@PathParam("idRestaurante") Long idRestaurante, PratoDTO dto) {
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
    public Response atualizarPrato(@PathParam("idRestaurante") Long idRestaurante, @PathParam("idPrato") Long idPrato, PratoDTO dto) {
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

        return Response.ok().build();
    }

    @DELETE
    @Path("{idRestaurante}/pratos/{idPrato}")
    @Tag(name = "prato")
    public Response deletarPrato(@PathParam("idRestaurante")  Long idRestaurante, @PathParam("idPrato") Long idPrato) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        if (restauranteOp.isEmpty()) {
            throw new NotFoundException("Restaurante não encontrado");
        }

        Optional<Prato> pratoOp = Prato.findByIdOptional(idPrato);
        if (pratoOp.isEmpty()) {
            throw new NotFoundException("Prato não encontrato");
        }

        pratoOp.get().delete();

        return Response.ok().build();
    }
}
