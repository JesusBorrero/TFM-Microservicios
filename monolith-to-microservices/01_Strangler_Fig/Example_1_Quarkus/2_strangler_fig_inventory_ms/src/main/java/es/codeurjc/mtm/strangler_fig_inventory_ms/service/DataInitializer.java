package es.codeurjc.mtm.strangler_fig_inventory_ms.service;

import es.codeurjc.mtm.strangler_fig_inventory_ms.model.Inventory;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DataInitializer {

  @Inject
  InventoryService inventoryService;

  @Transactional
  void onStart(@Observes StartupEvent ev) {
    Inventory inventory = Inventory.builder()
            .prize(1049.95)
            .description("[MS] Bicycle")
            .build();

    Inventory inventory2 = Inventory.builder()
            .prize(549.95)
            .description("[MS] Scooter")
            .build();

    Inventory inventory3 = Inventory.builder()
            .prize(4549.95)
            .description("[MS] Motorcycle")
            .build();

    inventoryService.saveInventory(inventory);
    inventoryService.saveInventory(inventory2);
    inventoryService.saveInventory(inventory3);
  }
}
