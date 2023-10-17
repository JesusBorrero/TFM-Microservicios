package es.codeurjc.mtm.strangler_fig_monolith.service;

import es.codeurjc.mtm.strangler_fig_monolith.model.Inventory;
import es.codeurjc.mtm.strangler_fig_monolith.model.Invoicing;
import es.codeurjc.mtm.strangler_fig_monolith.model.Payroll;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DataInitializer {

  @Inject
  InventoryService inventoryService;
  @Inject
  InvoicingService invoicingService;
  @Inject
  PayrollService payrollService;

  @Transactional
  void onStart(@Observes StartupEvent ev) {
    // Inventory
    Inventory inventory = Inventory.builder()
            .prize(1049.95)
            .description("Bicycle")
            .build();

    Inventory inventory2 = Inventory.builder()
            .prize(549.95)
            .description("Scooter")
            .build();

    Inventory inventory3 = Inventory.builder()
            .prize(4549.95)
            .description("Motorcycle")
            .build();

    inventoryService.saveInventory(inventory);
    inventoryService.saveInventory(inventory2);
    inventoryService.saveInventory(inventory3);

    // Invoicing
    Invoicing invoicing = Invoicing.builder()
            .billTo("[Monolith V2] user 1")
            .total(4549.95)
            .build();

    Invoicing invoicing2 = Invoicing.builder()
            .billTo("[Monolith V2] user 2")
            .total(549.95)
            .build();

    Invoicing invoicing3 = Invoicing.builder()
            .billTo("[Monolith V2] user 3")
            .total(1049.95)
            .build();

    invoicingService.saveInvoicing(invoicing);
    invoicingService.saveInvoicing(invoicing2);
    invoicingService.saveInvoicing(invoicing3);

    // Payroll
    Payroll payroll = Payroll.builder()
            .shipTo("[Monolith V2] user 1")
            .total(10549.95)
            .build();

    Payroll payroll2 = Payroll.builder()
            .shipTo("[Monolith V2] user 2")
            .total(6549.95)
            .build();

    Payroll payroll3 = Payroll.builder()
            .shipTo("[Monolith V2] user 3")
            .total(1449.95)
            .build();

    payrollService.savePayroll(payroll);
    payrollService.savePayroll(payroll2);
    payrollService.savePayroll(payroll3);
  }
}
