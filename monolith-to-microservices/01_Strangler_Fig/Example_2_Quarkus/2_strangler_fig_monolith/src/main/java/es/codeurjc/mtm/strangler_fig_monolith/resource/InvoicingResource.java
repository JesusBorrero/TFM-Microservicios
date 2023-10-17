package es.codeurjc.mtm.strangler_fig_monolith.resource;

import es.codeurjc.mtm.strangler_fig_monolith.model.Invoicing;
import es.codeurjc.mtm.strangler_fig_monolith.service.InvoicingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;

@Path("/invoicing")
public class InvoicingResource {
    @Inject
    InvoicingService invoicingService;

    @POST
    public Response createInvoicing(Invoicing invoicing, @Context UriInfo uriInfo) {
        this.invoicingService.saveInvoicing(invoicing);
        URI location = uriInfo.getAbsolutePathBuilder().path(Long.toString(invoicing.getId())).build();

        return Response.created(location).entity(invoicing).build();
    }

    @GET
    @Path("{id}")
    public Response getInvoicing(@PathParam("id") long id) {
        Invoicing invoicing = this.invoicingService.getInvoicing(id);

        if (invoicing != null) {
            return Response.ok(invoicing).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response getInvoicings() {
        return Response.ok(invoicingService.findAll()).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteInvoicing(@PathParam("id") long id) {
        Invoicing invoicing = this.invoicingService.getInvoicing(id);

        if (invoicing != null) {
            this.invoicingService.deleteInvoicing(id);
            return Response.ok(invoicing).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response replaceInvoicing(Invoicing invoicing, @PathParam("id") long id) {
        Invoicing invoicingUpdated = this.invoicingService.updateInvoicing(id, invoicing);

        if (invoicingUpdated != null) {
            return Response.ok(invoicing).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
}
