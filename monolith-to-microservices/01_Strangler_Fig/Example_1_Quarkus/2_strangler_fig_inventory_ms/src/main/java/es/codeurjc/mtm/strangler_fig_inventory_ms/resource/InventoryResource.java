package es.codeurjc.mtm.strangler_fig_inventory_ms.resource;

import es.codeurjc.mtm.strangler_fig_inventory_ms.model.Inventory;
import es.codeurjc.mtm.strangler_fig_inventory_ms.service.InventoryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;

@Path("/inventory")
public class InventoryResource {
    @Inject
    InventoryService inventoryService;

    @POST
    public Response createInventory(Inventory inventory, @Context UriInfo uriInfo) {
        this.inventoryService.saveInventory(inventory);
        URI location = uriInfo.getAbsolutePathBuilder().path(Long.toString(inventory.getId())).build();

        return Response.created(location).entity(inventory).build();
    }

    @GET
    @Path("{id}")
    public Response getInventory(@PathParam("id") long id) {
        Inventory inventory = this.inventoryService.getInventory(id);

        if (inventory != null) {
            return Response.ok(inventory).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response getInventories() {
        return Response.ok(inventoryService.findAll()).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteInventory(@PathParam("id") long id) {
        Inventory inventory = this.inventoryService.getInventory(id);

        if (inventory != null) {
            this.inventoryService.deleteInventory(id);
            return Response.ok(inventory).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response replaceInventory(Inventory inventory, @PathParam("id") long id) {
        Inventory inventoryUpdated = this.inventoryService.updateInventory(id, inventory);

        if (inventoryUpdated != null) {
            return Response.ok(inventory).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
}
