package es.codeurjc.mtm.strangler_fig_monolith.service;

import es.codeurjc.mtm.strangler_fig_monolith.model.Payroll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Collection;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
@Slf4j
public class PayrollService {

  private final ConcurrentMap<Long, Payroll> payrolls = new ConcurrentHashMap<>();
  private final AtomicLong nextId = new AtomicLong();

  @Inject
  @RestClient
  UserNotificationService userNotificationService;

  public Collection<Payroll> findAll() {
    return payrolls.values();
  }

  public void savePayroll(Payroll payroll) {
    long id = nextId.getAndIncrement();
    payroll.setId(id);
    this.payrolls.put(id, payroll);

    userNotificationService.notify(String.format("Payroll %s shipped to %s of %s", payroll.getId(), payroll.getShipTo(),
        payroll.getTotal()));
  }

  public Payroll getPayroll(Long id) {
    return this.payrolls.get(id);
  }

  public Payroll deletePayroll(Long id) {
    return this.payrolls.remove(id);
  }

  public Payroll updatePayroll(long id, Payroll payroll) {
    Payroll payrollStoraged = this.payrolls.get(id);
    if (payrollStoraged == null) {
      return null;
    } else {
      payrollStoraged.setShipTo(payroll.getShipTo());
      payrollStoraged.setTotal(payroll.getTotal());

      return payrollStoraged;
    }
  }
}
