package es.codeurjc.mtm.strangler_fig_monolith.resource;

import es.codeurjc.mtm.strangler_fig_monolith.model.Payroll;
import es.codeurjc.mtm.strangler_fig_monolith.service.PayrollService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;

@Path("/payroll")
public class PayrollResource {
    @Inject
    PayrollService payrollService;

    @POST
    public Response createPayroll(Payroll payroll, @Context UriInfo uriInfo) {
        this.payrollService.savePayroll(payroll);
        URI location = uriInfo.getAbsolutePathBuilder().path(Long.toString(payroll.getId())).build();

        return Response.created(location).entity(payroll).build();
    }

    @GET
    @Path("{id}")
    public Response getPayroll(@PathParam("id") long id) {
        Payroll payroll = this.payrollService.getPayroll(id);

        if (payroll != null) {
            return Response.ok(payroll).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response getPayrolls() {
        return Response.ok(payrollService.findAll()).build();
    }

    @DELETE
    @Path("{id}")
    public Response deletePayroll(@PathParam("id") long id) {
        Payroll payroll = this.payrollService.getPayroll(id);

        if (payroll != null) {
            this.payrollService.deletePayroll(id);
            return Response.ok(payroll).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response updatePayroll(Payroll Payroll, @PathParam("id") long id) {
        Payroll payrollUpdated = this.payrollService.updatePayroll(id, Payroll);

        if (payrollUpdated != null) {
            return Response.ok(Payroll).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
}
