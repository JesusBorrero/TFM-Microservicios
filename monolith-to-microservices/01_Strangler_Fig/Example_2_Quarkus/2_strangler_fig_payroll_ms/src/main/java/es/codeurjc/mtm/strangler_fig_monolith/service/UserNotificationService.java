package es.codeurjc.mtm.strangler_fig_monolith.service;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/notification")
@RegisterRestClient(configKey="user-notification-api")
public interface UserNotificationService {

  @POST
  String notify(String msg);

}
