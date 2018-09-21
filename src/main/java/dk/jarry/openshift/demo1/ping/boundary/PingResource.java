package dk.jarry.openshift.demo1.ping.boundary;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

/**
 * @author Michael Bornholdt Nielsen
 *
 */
@Singleton
@Path("ping")
public class PingResource {

	private static final Logger logger = Logger.getLogger(PingResource.class.getName());

	@Context
	HttpServletRequest request;

	@Context
	private Sse sse;

	@Inject
	PingService pingService;

	private volatile SseBroadcaster sseBroadcaster;

	@PostConstruct
	public void init() {
		logger.info("@PostConstruct");
		this.sseBroadcaster = sse.newBroadcaster();
	}

	@GET
	public Response getPing() {
		String message = ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
		return Response.ok(pingService.getPing(message)).build();
	}

	@GET
	@Path("/message/{message}")
	public Response getPingMessage(@PathParam("message") String message) {
		sseBroadcaster.broadcast(sse.newEvent(message));
		return Response.ok(pingService.getPing(message)).build();
	}

	@GET
	@Path("register")
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public void register(@Context SseEventSink eventSink) {
		eventSink.send(sse.newEvent("welcome as server side event!"));
		sseBroadcaster.register(eventSink);
	}

}
