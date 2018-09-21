package dk.jarry.openshift.demo1.ping.entity;

/**
 * @author Michael Bornholdt Nielsen
 *
 */
public class Ping {
	
	private String message;
	
	public Ping() {
	}

	public Ping(String message) {
		this.message = message;
	}
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

