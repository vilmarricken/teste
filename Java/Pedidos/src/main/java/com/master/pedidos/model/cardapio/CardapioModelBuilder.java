package com.master.pedidos.model.cardapio;

import java.util.Set;

import com.master.core.dao.DaoFactory;
import com.master.core.exception.MasterException;
import com.master.pedidos.dao.cardapio.CardapioDao;

public class CardapioModelBuilder {

	private static final String NL = "\n";

	public static CardapioModel build(String estabelecimento) throws MasterException {
		final CardapioDao dao = DaoFactory.getDao(CardapioDao.class);
		final CardapioModel model = dao.builModel(estabelecimento);
		final StringBuilder out = new StringBuilder(5 * 1024);
		CardapioModelBuilder.build(model, out);
		return model;
	}

	private static void build(CardapioModel model, StringBuilder out) {
		out.append("<!DOCTYPE html>").append(CardapioModelBuilder.NL);
		out.append("<html lang=\"pt\">").append(CardapioModelBuilder.NL);
		CardapioModelBuilder.buildHeader(model, out);
		CardapioModelBuilder.buildBody(model, out);
		out.append("</html>").append(CardapioModelBuilder.NL);
	}

	private static void buildBody(CardapioModel model, StringBuilder out) {
		out.append("<body>").append(CardapioModelBuilder.NL);
		CardapioModelBuilder.buildBodyHeader(model, out);
		CardapioModelBuilder.buildBodyNavigation(model, out);
		CardapioModelBuilder.builBodyContainer(model, out);
		out.append("</body>").append(CardapioModelBuilder.NL);
	}

	private static void builBodyContainer(CardapioModel model, StringBuilder out) {
		out.append("	<div class=\"container\">").append(CardapioModelBuilder.NL);
		out.append("	<div class=\"row\">").append(CardapioModelBuilder.NL);
		Set<Bloco> blocos = model.getBlocos();
		for (Bloco bloco : blocos) {
			buildBodyBloco(bloco, out);
		}
		out.append("	</div>").append(CardapioModelBuilder.NL);
		out.append("	</div>").append(CardapioModelBuilder.NL);
	}

	private static void buildBodyBloco(Bloco bloco, StringBuilder out) {
		<div class="panel panel-default">
		  <div class="panel-body">
		    Basic panel example
		  </div>
		</div>
		
		
		
		<div class="panel panel-default">
		  <!-- Default panel contents -->
		  <div class="panel-heading">Panel heading</div>

		  <!-- Table -->
		  <table class="table">
		    ...
		  </table>
		</div>
		
		
	}

	private static void buildBodyNavigation(CardapioModel model, StringBuilder out) {
		out.append("	<nav class=\"navbar navbar-inverse\">").append(CardapioModelBuilder.NL);
		out.append("		<div class=\"container-fluid\">").append(CardapioModelBuilder.NL);
		out.append("			<div class=\"navbar-header\">").append(CardapioModelBuilder.NL);
		out.append("				<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#myNavbar\">").append(CardapioModelBuilder.NL);
		out.append("					<span class=\"icon-bar\"></span>").append(CardapioModelBuilder.NL);
		out.append("					<span class=\"icon-bar\"></span>").append(CardapioModelBuilder.NL);
		out.append("					<span class=\"icon-bar\"></span>").append(CardapioModelBuilder.NL);
		out.append("				</button>").append(CardapioModelBuilder.NL);
		out.append("				<a class=\"navbar-brand\" href=\"#\">Logo</a>").append(CardapioModelBuilder.NL);
		out.append("			</div>").append(CardapioModelBuilder.NL);
		out.append("			<div class=\"collapse navbar-collapse\" id=\"myNavbar\">").append(CardapioModelBuilder.NL);
		out.append("				<ul class=\"nav navbar-nav\">").append(CardapioModelBuilder.NL);
		out.append("					<li class=\"active\"><a href=\"#\">Home</a></li>").append(CardapioModelBuilder.NL);
		out.append("					<li><a href=\"#\">Products</a></li>").append(CardapioModelBuilder.NL);
		out.append("					<li><a href=\"#\">Deals</a></li>").append(CardapioModelBuilder.NL);
		out.append("					<li><a href=\"#\">Stores</a></li>").append(CardapioModelBuilder.NL);
		out.append("					<li><a href=\"#\">Contact</a></li>").append(CardapioModelBuilder.NL);
		out.append("				</ul>").append(CardapioModelBuilder.NL);
		out.append("				<ul class=\"nav navbar-nav navbar-right\">").append(CardapioModelBuilder.NL);
		out.append("					<li><a href=\"#\"><span class=\"glyphicon glyphicon-user\"></span> Your Account</a></li>").append(CardapioModelBuilder.NL);
		out.append("					<li><a href=\"#\"><span class=\"glyphicon glyphicon-shopping-cart\"></span> Cart</a></li>").append(CardapioModelBuilder.NL);
		out.append("				</ul>").append(CardapioModelBuilder.NL);
		out.append("			</div>").append(CardapioModelBuilder.NL);
		out.append("		</div>").append(CardapioModelBuilder.NL);
		out.append("	</nav>").append(CardapioModelBuilder.NL);
	}

	private static void buildBodyHeader(CardapioModel model, StringBuilder out) {
		out.append("	<div class=\"jumbotron\">").append(CardapioModelBuilder.NL);
		out.append("		<div class=\"container text-center\">").append(CardapioModelBuilder.NL);
		out.append("			<h1>");
		out.append(model.getTitulo());
		out.append("</h1>").append(CardapioModelBuilder.NL);
		final String descricao = model.getDescricao();
		if (descricao != null) {
			out.append("			<p>");
			out.append(descricao);
			out.append("</p>").append(CardapioModelBuilder.NL);
		}
		out.append("		</div>").append(CardapioModelBuilder.NL);
		out.append("	</div>").append(CardapioModelBuilder.NL);
	}

	private static void buildHeader(CardapioModel model, StringBuilder out) {
		out.append("<head>").append(CardapioModelBuilder.NL);
		out.append("	<title>Bootstrap Example</title>").append(CardapioModelBuilder.NL);
		out.append("	<meta charset=\"utf-8\">").append(CardapioModelBuilder.NL);
		out.append("	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">").append(CardapioModelBuilder.NL);
		out.append("	<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">").append(CardapioModelBuilder.NL);
		out.append("	<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>").append(CardapioModelBuilder.NL);
		out.append("	<script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>").append(CardapioModelBuilder.NL);
		out.append("	<style>").append(CardapioModelBuilder.NL);
		/*
		 * Remove the navbar's default rounded borders and increase the bottom
		 * margin
		 */
		out.append("		.navbar {").append(CardapioModelBuilder.NL);
		out.append("			margin-bottom: 50px;").append(CardapioModelBuilder.NL);
		out.append("			border-radius: 0;").append(CardapioModelBuilder.NL);
		out.append("		}").append(CardapioModelBuilder.NL);
		/* Remove the jumbotron's default bottom margin */
		out.append("		.jumbotron {").append(CardapioModelBuilder.NL);
		out.append("			margin-bottom: 0;").append(CardapioModelBuilder.NL);
		out.append("		}").append(CardapioModelBuilder.NL);
		/* Add a gray background color and some padding to the footer */
		out.append("		footer {").append(CardapioModelBuilder.NL);
		out.append("			background-color: #f2f2f2;").append(CardapioModelBuilder.NL);
		out.append("			padding: 25px;").append(CardapioModelBuilder.NL);
		out.append("		}").append(CardapioModelBuilder.NL);
		out.append("	</style>").append(CardapioModelBuilder.NL);
		out.append("</head>").append(CardapioModelBuilder.NL);
	}

}
