package dk.jarry.openshift.demo1.ping.boundary;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author Michael Bornholdt Nielsen
 *
 */
@ServerEndpoint("/ping")
public class PingWebSocketServer {

	public static List<Session> sessionList = new ArrayList<>();

	@OnOpen
	public void connect(Session session) {
		sessionList.add(session);

		JsonObjectBuilder joBuilder = Json.createObjectBuilder();
		joBuilder.add("message", "welcome as WebSocket! ");

		session.getAsyncRemote().sendText(joBuilder.build().toString());
	}

	@OnClose
	public void close(Session session) {
		sessionList.remove(session);
	}

}
