package es.codeurjc.mtm.strangler_fig_cbr.queue;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
@Slf4j
public class KafkaConsumer {
  @Inject
  KafkaProducer kafkaProducer;

  @Incoming("payroll-ingoing")
  public void payrollListener(String message) {
    kafkaProducer.sendPayrollMessage(message);
  }

  @Incoming("invoicing-ingoing")
  public void invoicingListener(String message) {
    kafkaProducer.sendInvoicingMessage(message);
  }
}