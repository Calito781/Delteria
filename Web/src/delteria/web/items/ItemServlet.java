package delteria.web.items;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delteria.repo.file.File;
import delteria.repo.file.FileRepository;
import delteria.repo.items.Item;
import delteria.repo.items.ItemRepository;

@WebServlet(urlPatterns="/item.jsp")
public class ItemServlet extends HttpServlet {

	private static final long serialVersionUID = 8060795477260698416L;

	@Inject ItemRepository items;
	@Inject FileRepository files;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String item_id = (String) request.getParameter("item_id");
		Item item = items.get(item_id);
		File icon = null;
		if (item != null) {
			icon = files.get(item.icon_file);
		}

		request.setAttribute("property_formatter", new PropertyFormatter());
		request.setAttribute("item", item);
		request.setAttribute("icon", icon);
		request.getRequestDispatcher("item/viewItem.jsp").forward(request, response);
	}
}
