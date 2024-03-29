package br.fiap.gff.payments.application.controllers;


import br.fiap.gff.payments.models.Receipt;
import br.fiap.gff.payments.usecases.ReceiptUseCase;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Path("/receipts")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ReceiptController {

    private final ReceiptUseCase receipts;

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") String id) {
        Receipt r = receipts.getById(id);
        return Response.ok(r).build();
    }

}
