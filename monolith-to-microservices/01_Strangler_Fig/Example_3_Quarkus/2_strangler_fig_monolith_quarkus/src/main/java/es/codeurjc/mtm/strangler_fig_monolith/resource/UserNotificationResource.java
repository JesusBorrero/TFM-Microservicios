package es.codeurjc.mtm.strangler_fig_monolith.resource;

import es.codeurjc.mtm.strangler_fig_monolith.service.UserNotificationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/notification")
public class UserNotificationResource {

  @Inject
  UserNotificationService userNotificationService;

  @POST
  public Response notify(String msg) {
    this.userNotificationService.notify(msg);

    return Response.ok().entity(msg).build();
  }

}
