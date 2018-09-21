package dk.jarry.openshift.demo1.ping.boundary;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import dk.jarry.openshift.demo1.ping.entity.Ping;

/**
 * @author Michael Bornholdt Nielsen
 *
 */
@Stateless
public class PingService {

	private static final Logger logger = Logger.getLogger(PingService.class.getName());

	@Inject
	private Event<Ping> pingEvent;

	@Inject
	private Event<JsonObject> jsonObjectEvent;

	public PingService() {
		logger.info("Just created ");
	}

	@PostConstruct
	public void init() {
		logger.info("@PostConstruct");
	}

	public JsonObject getPing(String message) {

		JsonObjectBuilder joBuilder = Json.createObjectBuilder();
		joBuilder.add("message", message);

		JsonObject jsonObejct = joBuilder.build();

		pingEvent.fireAsync(new Ping(jsonObejct.toString()));
		jsonObjectEvent.fireAsync(jsonObejct);

		return jsonObejct;
	}

}