package delteria.gserver.util;

import java.net.Socket;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.net.SocketFactory;

@Singleton
public class GSocketProvider implements Provider<Socket> {

	private final SocketFactory socketFactory;
	private Socket existingConnection = null;

	@Inject GSocketProvider(SocketFactory socketFactory) {
		this.socketFactory = socketFactory;
	}

	@Override
	@Produces
	public Socket get() {
		// If connection died, start a new one!
		if (existingConnection == null || existingConnection.isClosed()) {
			try {
				existingConnection = socketFactory.createSocket("50.22.4.34", 2002);
				existingConnection.setKeepAlive(true);
				existingConnection.setTcpNoDelay(true);

				try {
					Thread.sleep(250); // Allow server time to boot up
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				return existingConnection;
			} catch (Exception e) {
				throw new RuntimeException("Could not create socket.", e);
			}
		}
		
		return existingConnection;
	}

}
