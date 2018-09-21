package dk.jarry.openshift.demo1.ping.control;

import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.enterprise.event.ObservesAsync;
import javax.json.JsonObject;

import dk.jarry.openshift.demo1.ping.boundary.PingWebSocketServer;
import dk.jarry.openshift.demo1.ping.entity.Ping;

/**
 * @author Michael Bornholdt Nielsen
 *
 */
public class PingEvent {

	private static final Logger logger = Logger.getLogger(PingEvent.class.getName());

	public PingEvent() {
	}

	public void onPingNotification(@Priority(10) @ObservesAsync Ping ping) {
		logger.info("Low (10): " + ping.getMessage());
	}

	public void onPingNotificationHigh(@Priority(1) @ObservesAsync Ping ping) {
		
		logger.info("High (1): " + ping.getMessage());		

	}

	public void onPingNotification(@ObservesAsync JsonObject jsonObject) {
		
		PingWebSocketServer.sessionList.stream().forEach((ses) -> {
			ses.getAsyncRemote().sendText(jsonObject.toString());
		});
		
		logger.info(jsonObject.toString());
	}

}

