package es.codeurjc.mtm.strangler_fig_monolith.service;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class UserNotificationService {

  public void notify(String message) {
    log.info(message);
  }
}
