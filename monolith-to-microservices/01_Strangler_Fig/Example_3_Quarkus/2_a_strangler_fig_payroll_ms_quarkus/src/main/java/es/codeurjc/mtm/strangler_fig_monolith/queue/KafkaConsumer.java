package es.codeurjc.mtm.strangler_fig_monolith.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.codeurjc.mtm.strangler_fig_monolith.model.Payroll;
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
  PayrollService payrollService;

  @Incoming("payroll")
  public void payrollListener(String message) throws JsonProcessingException {
    Payroll payroll = this.mapper.readValue(message, Payroll.class);
    payrollService.savePayroll(payroll);
  }
}