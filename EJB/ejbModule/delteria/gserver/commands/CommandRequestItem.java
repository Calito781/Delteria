package delteria.gserver.commands;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Future;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import delteria.concurrent.FutureValue;
import delteria.gserver.CommunicationListener;
import delteria.gserver.GServerConnection;
import delteria.gserver.socket.CommunicationException;
import delteria.repo.items.Item;

public class CommandRequestItem implements Command<Item> {

	private final String item_id;
	public CommandRequestItem(String item_id) {
		this.item_id = item_id;
	}

	@Override
	public Future<Item> sendCommand(GServerConnection connection)
			throws CommunicationException {
		JsonArray command = new JsonArray();
		command.add(new JsonPrimitive("item"));
		command.add(new JsonPrimitive(item_id));
		
		FutureValue<Item> future_value = new FutureValue<Item>();
		connection.addListener(new Listener(future_value));
		connection.sendCommand(command);

		return future_value;
	}
	
	public class Listener implements CommunicationListener {

		private final FutureValue<Item> future_value;
		
		public Listener(FutureValue<Item> future_value) {
			this.future_value = future_value;
		}

		@Override
		public void onReceivedMessage(JsonElement message) {
			if (!(message instanceof JsonArray)) {
				return;
			}
			JsonArray command = (JsonArray) message;
			
			if (! "item".equals(command.get(0).getAsString())) {
				return;
			}
			
			if (! item_id.equals(command.get(1).getAsString())) {
				return;
			}
			
			Map<String, String> item_properties = new HashMap<String, String>();
			if ( command.get(6).isJsonArray()) {
				JsonArray properties = command.get(6).getAsJsonArray();
				Iterator<JsonElement> properties_iterator = properties.iterator();
				while (properties_iterator.hasNext()) {
					JsonArray property = properties_iterator.next().getAsJsonArray();
					item_properties.put(property.get(0).getAsString(), property.get(1).isJsonPrimitive() ? property.get(1).getAsString() : property.get(1).toString());
				}
			}
			
			future_value.set(new Item(
				item_id,
				command.get(2).getAsString(),
				command.get(3).getAsString(),
				command.get(4).getAsString(),
				command.get(5).getAsInt() == 1,
				item_properties
			));
		}

		@Override
		public boolean isExpired() {
			return future_value.isCancelled() || future_value.isDone();
		}
	}
}
