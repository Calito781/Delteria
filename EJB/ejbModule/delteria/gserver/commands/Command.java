package delteria.gserver.commands;

import java.util.concurrent.Future;

import delteria.gserver.GServerConnection;
import delteria.gserver.socket.CommunicationException;

public interface Command<Response> {
	public Future<Response> sendCommand(GServerConnection connection) throws CommunicationException;
}
