package delteria.gserver.socket;

public class CommunicationException extends Exception {

	private static final long serialVersionUID = 3849339355416156759L;

	CommunicationException(Exception e) {
		super("Could not communicate successfully.", e);
	}
}
