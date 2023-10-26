package es.codeurjc.mtm.strangler_fig_monolith.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.codeurjc.mtm.strangler_fig_monolith.model.Invoicing;
import es.codeurjc.mtm.strangler_fig_monolith.model.Payroll;
import es.codeurjc.mtm.strangler_fig_monolith.service.InvoicingService;
import es.codeurjc.mtm.strangler_fig_monolith.service.PayrollService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
@Slf4j
public class KafkaConsumer {

  ObjectMapper mapper = new ObjectMapper();

  @Inject
  InvoicingService invoicingService;

  @Incoming("invoicing")
  public void invoicingListener(String message) throws JsonProcessingException {
    Invoicing invoicing = this.mapper.readValue(message, Invoicing.class);
    invoicingService.saveInvoicing(invoicing);
  }
}