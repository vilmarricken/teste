package com.master.pedeai.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.core.resource.ResourceFacotry;
import com.master.pedeai.resource.ResourceWeb;

public class CardapioServlet extends HttpServlet {

	public CardapioServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResourceWeb resource = ResourceFacotry.get();
		resource.getEstabelecimento();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
