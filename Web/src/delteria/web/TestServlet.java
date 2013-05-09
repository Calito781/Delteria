package delteria.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delteria.repo.Test;


@WebServlet(urlPatterns="/test")
public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 691570072059594363L;

	@Inject Bean bean;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getOutputStream().write(bean.getMessage().getBytes());
	}
}
