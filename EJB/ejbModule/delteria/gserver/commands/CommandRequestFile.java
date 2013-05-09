package delteria.gserver.commands;

import java.util.concurrent.Future;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import delteria.concurrent.FutureValue;
import delteria.gserver.CommunicationListener;
import delteria.gserver.GServerConnection;
import delteria.gserver.socket.CommunicationException;
import delteria.repo.file.File;

public class CommandRequestFile implements Command<File> {

	private final String filename;
	
	public CommandRequestFile(String filename) {
		this.filename = filename;
	}

	@Override
	public Future<File> sendCommand(GServerConnection connection)
			throws CommunicationException {
		JsonArray command = new JsonArray();
		command.add(new JsonPrimitive("file"));
		command.add(new JsonPrimitive(filename));

		FutureValue<File> future_value = new FutureValue<File>();
		connection.addListener(new Listener(future_value));
		
		connection.sendCommand(command);

		return future_value;
	}
	
	private class Listener implements CommunicationListener {
		private final FutureValue<File> future_value;

		public Listener(FutureValue<File> future_value) {
			this.future_value = future_value;
		}

		@Override
		public void onReceivedMessage(JsonElement message) {
			if (!(message instanceof JsonArray)) return;
			JsonArray command = (JsonArray) message;
			if (! "file".equals(command.get(0).getAsString())) return;
			if (! filename.equals(command.get(1).getAsString())) return;
			
			try {
				future_value.set(new File(
					command.get(1).getAsString(),
					Base64.decode(command.get(2).getAsString().getBytes())
				));
			} catch (Base64DecodingException e) {
			}
		}

		@Override
		public boolean isExpired() {
			return future_value.isCancelled() || future_value.isDone();
		}		
	}
}
