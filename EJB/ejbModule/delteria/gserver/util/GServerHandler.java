package delteria.gserver.util;

import javax.inject.Inject;

import delteria.gserver.GServerConnection;
import delteria.gserver.socket.CommunicationException;

public class GServerHandler implements Runnable {

	private final GServerConnection connection;

	@Inject GServerHandler(GServerConnection connection) {
		this.connection = connection;
	}
	
	@Override
	public void run() {
		try {
			while ( ! Thread.interrupted()) {
				try {
					connection.processMessage();
				} catch (CommunicationException e) {
					e.printStackTrace();
				}
				
				Thread.yield();
			}
	
			Thread.currentThread().interrupt();
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
