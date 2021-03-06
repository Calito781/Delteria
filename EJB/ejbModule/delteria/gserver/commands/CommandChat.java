package delteria.gserver.commands;

import java.util.concurrent.Future;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import delteria.gserver.GServerConnection;
import delteria.gserver.socket.CommunicationException;

public class CommandChat implements Command<Void> {

	private final String message;

	public CommandChat(String message) {
		this.message = message;
	}
	
	@Override
	public Future<Void> sendCommand(GServerConnection connection)
			throws CommunicationException {
		JsonArray command = new JsonArray();
		command.add(new JsonPrimitive("chat"));
		command.add(new JsonPrimitive(message));
		
		connection.sendCommand(command);
		return null;
	}

}
