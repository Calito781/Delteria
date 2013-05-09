package delteria.web.file;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delteria.repo.file.File;
import delteria.repo.file.FileRepository;

@WebServlet(urlPatterns="/game/image.png")
public class FileServlet extends HttpServlet {

	private static final long serialVersionUID = -1160137263278144111L;

	@Inject FileRepository files;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String filename = (String) request.getParameter("filename");
		File file = files.get(filename);

		if (file != null) {
			response.addHeader("Content-Type", "image/png");
			response.addDateHeader("Expires", System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES));
			response.getOutputStream().write(file.content);
			response.getOutputStream().flush();
		} else {
			response.addHeader("Status-Code", "503");
		}
	}
}
