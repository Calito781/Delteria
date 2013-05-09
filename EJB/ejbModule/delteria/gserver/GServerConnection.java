package delteria.gserver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gson.JsonElement;

import delteria.gserver.commands.CommandChat;
import delteria.gserver.commands.CommandRequestCard;
import delteria.gserver.commands.CommandRequestFile;
import delteria.gserver.commands.CommandRequestItem;
import delteria.gserver.commands.CommandRequestItems;
import delteria.gserver.socket.AlwaysAliveSocket;
import delteria.gserver.socket.CommunicationException;
import delteria.gserver.util.GServerAdapter;
import delteria.gserver.util.IncomingLogger;
import delteria.repo.card.Card;
import delteria.repo.file.File;
import delteria.repo.items.Item;

@Singleton
@LocalBean
public class GServerConnection {

	private final AlwaysAliveSocket connection;
	private final GServerAdapter adapter;
	private final List<CommunicationListener> listeners = new ArrayList<CommunicationListener>();

	@Inject GServerConnection(AlwaysAliveSocket connection, GServerAdapter adapter) {
		this.connection = connection;
		this.adapter = adapter;

		addListener(new IncomingLogger());
	}
	
	public void processMessage() throws CommunicationException {
		JsonElement message;
		try {
			message = adapter.convertFromServer(connection.read());
		} catch (RuntimeException e) {
			e.printStackTrace();
			return;
		}

		synchronized (listeners) {
			Iterator<CommunicationListener> iterator = listeners.iterator();
			while (iterator.hasNext()) {
				CommunicationListener listener = iterator.next();
				if (listener.isExpired()) {
					iterator.remove();
				} else {
					try {
						listener.onReceivedMessage(message);
					} catch (RuntimeException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void sendCommand(JsonElement command) throws CommunicationException {
		System.out.println("Outgoing: "+ command.toString());
		connection.write(adapter.convertToServer(command));
	}

	public void addListener(CommunicationListener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}
	
	// ~ Commands
	
	public void sendChat(String message) throws CommunicationException {
		new CommandChat(message).sendCommand(this);
	}

	public Future<Card> requestCard(final String account) throws CommunicationException {
		return new CommandRequestCard(account).sendCommand(this);
	}

	public Future<Item> requestItem(String item_id) throws CommunicationException {
		return new CommandRequestItem(item_id).sendCommand(this);
	}

	public Future<List<String>> requestItems() throws CommunicationException {
		return new CommandRequestItems().sendCommand(this);
	}
	
	public Future<File> requestFile(String filename) throws CommunicationException {
		return new CommandRequestFile(filename).sendCommand(this);
	}
}
