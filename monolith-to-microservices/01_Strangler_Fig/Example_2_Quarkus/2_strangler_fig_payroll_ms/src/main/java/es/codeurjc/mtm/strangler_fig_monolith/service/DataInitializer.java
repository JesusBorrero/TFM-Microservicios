package es.codeurjc.mtm.strangler_fig_monolith.service;

import es.codeurjc.mtm.strangler_fig_monolith.model.Payroll;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DataInitializer {

  @Inject
  PayrollService payrollService;

  @Transactional
  void onStart(@Observes StartupEvent ev) {
    // Payroll
    Payroll payroll = Payroll.builder()
            .shipTo("[MS] user 1")
            .total(10549.95)
            .build();

    Payroll payroll2 = Payroll.builder()
            .shipTo("[MS] user 2")
            .total(6549.95)
            .build();

    Payroll payroll3 = Payroll.builder()
            .shipTo("[MS] user 3")
            .total(1449.95)
            .build();

    payrollService.savePayroll(payroll);
    payrollService.savePayroll(payroll2);
    payrollService.savePayroll(payroll3);
  }
}
