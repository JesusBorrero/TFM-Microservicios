package es.codeurjc.mtm.strangler_fig_producer.resource;

import es.codeurjc.mtm.strangler_fig_producer.queue.KafkaProducer;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/messages")
public class KafkaResource {
  @Inject
  KafkaProducer kafkaProducer;

  @POST
  @Path("send-payroll")
  public Response sendPayrollMessage(String message) {
    kafkaProducer.sendPayrollMessage(message);
    return Response.ok(message).build();
  }

  @POST
  @Path("send-invoicing")
  public Response sendInvoicingMessage(String message) {
    kafkaProducer.sendInvoicingMessage(message);
    return Response.ok(message).build();
  }
}