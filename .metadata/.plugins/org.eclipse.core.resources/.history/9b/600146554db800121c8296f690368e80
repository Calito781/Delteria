package delteria.gserver.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import delteria.concurrent.FutureValue;
import delteria.gserver.CommunicationListener;
import delteria.gserver.GServerConnection;
import delteria.gserver.socket.CommunicationException;

public class CommandRequestItems implements Command<List<String>> {

	@Override
	public Future<List<String>> sendCommand(GServerConnection connection)
			throws CommunicationException {
		JsonArray command = new JsonArray();
		command.add(new JsonPrimitive("items"));

		FutureValue<List<String>> future_value = new FutureValue<List<String>>();
		connection.addListener(new Listener(future_value));
		connection.sendCommand(command);
		
		return future_value;
	}
	
	public class Listener implements CommunicationListener {
		private final FutureValue<List<String>> future_value;

		public Listener(FutureValue<List<String>> future_value) {
			this.future_value = future_value;
		}
		
		@Override
		public void onReceivedMessage(JsonElement message) {
			if (!(message instanceof JsonArray)) return;
			JsonArray command = (JsonArray) message;
			
			if ( ! "items".equals(command.get(0).getAsString())) return;
			
			List<String> item_list = new ArrayList<String>();
			JsonArray items = command.get(1).getAsJsonArray();
			for (JsonElement item: items) {
				item_list.add(item.getAsString());
			}
			
			future_value.set(item_list);
		}

		@Override
		public boolean isExpired() {
			return future_value.isCancelled() || future_value.isDone();
		}
		
	}

}
