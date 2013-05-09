package delteria.web.items;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delteria.repo.items.Item;
import delteria.repo.items.ItemRepository;

@WebServlet(urlPatterns="/items.jsp")
public class ItemsServlet extends HttpServlet {

	private static final long serialVersionUID = -7229176987914516554L;

	@Inject ItemRepository items;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		List<String> item_id_list = items.getAll();
		List<Item> item_list = new ArrayList<Item>();
		for (String item_id: item_id_list) {
			item_list.add(items.get(item_id));
		}

		request.setAttribute("items", item_list);
		request.getRequestDispatcher("item/viewItemList.jsp").forward(request, response);
	}
}
