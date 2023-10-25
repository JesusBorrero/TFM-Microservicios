package es.codeurjc.mtm.strangler_fig_monolith.controller;

import es.codeurjc.mtm.strangler_fig_monolith.model.Invoicing;
import es.codeurjc.mtm.strangler_fig_monolith.service.InvoicingService;
import java.util.Collection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoicing")
public class InvoicingController {

  private final InvoicingService invoicingService;

  public InvoicingController(
      InvoicingService invoicingService) {
    this.invoicingService = invoicingService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Invoicing> getInvoicing(
      @PathVariable long id) {
    Invoicing invoicing = this.invoicingService.getInvoicing(id);

    if (invoicing != null) {
      return ResponseEntity.ok(this.invoicingService.getInvoicing(id));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping({""})
  public ResponseEntity<Collection<Invoicing>> getInvoicings() {
    return ResponseEntity.ok(invoicingService.findAll());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Invoicing> deleteInvoicing(
      @PathVariable long id) {
    Invoicing invoicing = this.invoicingService.getInvoicing(id);

    if (invoicing != null) {
      this.invoicingService.deleteInvoicing(id);
      return ResponseEntity.ok(invoicing);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping({"/{id}"})
  public ResponseEntity<Invoicing> createInvoicing(@RequestBody Invoicing invoicing,
      @PathVariable long id) {
    Invoicing invoicingUpdated = this.invoicingService.updateInvoicing(id, invoicing);

    if (invoicingUpdated != null) {
      return ResponseEntity.ok(invoicing);
    } else {
      return ResponseEntity.notFound().build();
    }

  }

}
