package delteria.repo.file;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import delteria.gserver.GServerConnection;
import delteria.gserver.socket.CommunicationException;

public class FileRepository {

	@PersistenceContext(unitName="myPU")
	private EntityManager em;

	private GServerConnection server;

	@Inject public FileRepository(GServerConnection server, EntityManager em) {
		this.em = em;
		this.server = server;
	}
	
	public File get(String filename) {
		File file = em.find(File.class, filename);
		
		// If we don't have the item cached, load it from the server
		if (file == null) {
			file = loadFromGame(filename);
			if (file != null) {
				file = em.merge(file);
			}
		}
		
		return file;
	}

	private File loadFromGame(String filename) {
		File file = null;
		Future<File> future_file = null;
		try {
			future_file = server.requestFile(filename);
			file = future_file.get(10, TimeUnit.SECONDS);
		} catch (TimeoutException e) {
			future_file.cancel(true);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (CommunicationException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return file;
	}
}
