package com.master.pedeai.cardapio.builder;

import java.text.NumberFormat;
import java.util.Set;

import com.master.core.dao.DaoFactory;
import com.master.core.exception.MasterException;
import com.master.pedeai.bloco.model.BlocoModel;
import com.master.pedeai.cardapio.dao.CardapioDao;
import com.master.pedeai.cardapio.model.CardapioModel;
import com.master.pedeai.produto.model.Produto;

public class CardapioModelBuilderGrid {

	private static final String NL = "\n";

	public String build(String estabelecimento) throws MasterException {
		final CardapioDao dao = DaoFactory.getDao(CardapioDao.class);
		final CardapioModel model = dao.builModel(estabelecimento);
		final StringBuilder out = new StringBuilder(5 * 1024);
		this.build(model, out);
		return out.toString();
	}

	private void build(CardapioModel model, StringBuilder out) {
		out.append("<!DOCTYPE html>").append(CardapioModelBuilderGrid.NL);
		out.append("<html lang=\"pt\">").append(CardapioModelBuilderGrid.NL);
		this.buildHeader(model, out);
		this.buildBody(model, out);
		out.append("</html>").append(CardapioModelBuilderGrid.NL);
	}

	private void buildBody(CardapioModel model, StringBuilder out) {
		out.append("<body>").append(CardapioModelBuilderGrid.NL);
		this.buildBodyHeader(model, out);
		this.buildBodyNavigation(model, out);
		this.builBodyContainer(model, out);
		out.append("</body>").append(CardapioModelBuilderGrid.NL);
	}

	private void builBodyContainer(CardapioModel model, StringBuilder out) {
		out.append("	<div class=\"container\">").append(CardapioModelBuilderGrid.NL);
		out.append("	<div class=\"row\">").append(CardapioModelBuilderGrid.NL);
		final Set<BlocoModel> blocos = model.getBlocos();
		for (final BlocoModel bloco : blocos) {
			final Set<BlocoModel> filhos = bloco.getFillhos();
			for (final BlocoModel filho : filhos) {
				out.append("	<div class=\"col-md-6\">").append(CardapioModelBuilderGrid.NL);
				this.buildBodyBloco(filho, out);
				out.append("	</div>").append(CardapioModelBuilderGrid.NL);
			}
		}
		out.append("	</div>").append(CardapioModelBuilderGrid.NL);
		out.append("	</div>").append(CardapioModelBuilderGrid.NL);
	}

	private void buildBodyBloco(BlocoModel bloco, StringBuilder out) {
		out.append("<table width=\"100%\" border=\"1\">").append(CardapioModelBuilderGrid.NL);
		out.append("<tr>").append(CardapioModelBuilderGrid.NL);
		out.append("	<td colspan=\"4\" align=\"center\">").append(bloco.getTitulo()).append("</td>").append(CardapioModelBuilderGrid.NL);
		out.append("</tr>").append(CardapioModelBuilderGrid.NL);
		final Set<Produto> produtos = bloco.getProduto();
		if (produtos != null) {
			for (final Produto produto : produtos) {
				this.buildBodyProduto(produto, out);
			}
		}
		out.append("</table>").append(CardapioModelBuilderGrid.NL);
	}

	private void buildBodyProduto(Produto produto, StringBuilder out) {
		out.append("<tr>").append(CardapioModelBuilderGrid.NL);
		out.append("<td width=\"10%\">").append(produto.getCodigo()).append("</td>").append(CardapioModelBuilderGrid.NL);
		out.append("<td>").append(produto.getNome()).append("</td>").append(CardapioModelBuilderGrid.NL);
		out.append("<td width=\"20%\" nowrap=\"true\">").append(NumberFormat.getCurrencyInstance().format(10)).append("</td>").append(CardapioModelBuilderGrid.NL);
		out.append("<td width=\"20%\" nowrap=\"true\"><input type=\"text\" value=\"1\" maxwidth=\"2\" size=\"2\">&nbsp;Add</td>").append(CardapioModelBuilderGrid.NL);
		out.append("</tr>").append(CardapioModelBuilderGrid.NL);
		out.append("<tr>").append(CardapioModelBuilderGrid.NL);
		final String descricao = produto.getDescricao();
		if (descricao != null) {
			out.append("	<td colspan=\"4\">").append(descricao).append("</td>").append(CardapioModelBuilderGrid.NL);
		}
		out.append("</tr>").append(CardapioModelBuilderGrid.NL);
	}

	private void buildBodyNavigation(CardapioModel model, StringBuilder out) {
		out.append("	<nav class=\"navbar navbar-inverse\">").append(CardapioModelBuilderGrid.NL);
		out.append("		<div class=\"container-fluid\">").append(CardapioModelBuilderGrid.NL);
		out.append("			<div class=\"navbar-header\">").append(CardapioModelBuilderGrid.NL);
		out.append("				<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#myNavbar\">").append(CardapioModelBuilderGrid.NL);
		out.append("					<span class=\"icon-bar\"></span>").append(CardapioModelBuilderGrid.NL);
		out.append("					<span class=\"icon-bar\"></span>").append(CardapioModelBuilderGrid.NL);
		out.append("					<span class=\"icon-bar\"></span>").append(CardapioModelBuilderGrid.NL);
		out.append("				</button>").append(CardapioModelBuilderGrid.NL);
		out.append("				<a class=\"navbar-brand\" href=\"#\">Logo</a>").append(CardapioModelBuilderGrid.NL);
		out.append("			</div>").append(CardapioModelBuilderGrid.NL);
		out.append("			<div class=\"collapse navbar-collapse\" id=\"myNavbar\">").append(CardapioModelBuilderGrid.NL);
		out.append("				<ul class=\"nav navbar-nav\">").append(CardapioModelBuilderGrid.NL);
		out.append("					<li class=\"active\"><a href=\"#\">Home</a></li>").append(CardapioModelBuilderGrid.NL);
		out.append("					<li><a href=\"#\">Products</a></li>").append(CardapioModelBuilderGrid.NL);
		out.append("					<li><a href=\"#\">Deals</a></li>").append(CardapioModelBuilderGrid.NL);
		out.append("					<li><a href=\"#\">Stores</a></li>").append(CardapioModelBuilderGrid.NL);
		out.append("					<li><a href=\"#\">Contact</a></li>").append(CardapioModelBuilderGrid.NL);
		out.append("				</ul>").append(CardapioModelBuilderGrid.NL);
		out.append("				<ul class=\"nav navbar-nav navbar-right\">").append(CardapioModelBuilderGrid.NL);
		out.append("					<li><a href=\"#\"><span class=\"glyphicon glyphicon-user\"></span> Your Account</a></li>").append(CardapioModelBuilderGrid.NL);
		out.append("					<li><a href=\"#\"><span class=\"glyphicon glyphicon-shopping-cart\"></span> Cart</a></li>").append(CardapioModelBuilderGrid.NL);
		out.append("				</ul>").append(CardapioModelBuilderGrid.NL);
		out.append("			</div>").append(CardapioModelBuilderGrid.NL);
		out.append("		</div>").append(CardapioModelBuilderGrid.NL);
		out.append("	</nav>").append(CardapioModelBuilderGrid.NL);
	}

	private void buildBodyHeader(CardapioModel model, StringBuilder out) {
		out.append("	<div class=\"jumbotron\">").append(CardapioModelBuilderGrid.NL);
		out.append("		<div class=\"container text-center\">").append(CardapioModelBuilderGrid.NL);
		out.append("			<h1>");
		out.append(model.getTitulo());
		out.append("</h1>").append(CardapioModelBuilderGrid.NL);
		final String descricao = model.getDescricao();
		if (descricao != null) {
			out.append("			<p>");
			out.append(descricao);
			out.append("</p>").append(CardapioModelBuilderGrid.NL);
		}
		out.append("		</div>").append(CardapioModelBuilderGrid.NL);
		out.append("	</div>").append(CardapioModelBuilderGrid.NL);
	}

	private void buildHeader(CardapioModel model, StringBuilder out) {
		out.append("<head>").append(CardapioModelBuilderGrid.NL);
		out.append("	<title>Bootstrap Example</title>").append(CardapioModelBuilderGrid.NL);
		out.append("	<meta charset=\"iso-8859-1\">").append(CardapioModelBuilderGrid.NL);
		out.append("	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">").append(CardapioModelBuilderGrid.NL);
		out.append("	<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">").append(CardapioModelBuilderGrid.NL);
		out.append("	<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>").append(CardapioModelBuilderGrid.NL);
		out.append("	<script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>").append(CardapioModelBuilderGrid.NL);
		out.append("	<style>").append(CardapioModelBuilderGrid.NL);
		/*
		 * Remove the navbar's default rounded borders and increase the bottom
		 * margin
		 */
		out.append("		.navbar {").append(CardapioModelBuilderGrid.NL);
		out.append("			margin-bottom: 50px;").append(CardapioModelBuilderGrid.NL);
		out.append("			border-radius: 0;").append(CardapioModelBuilderGrid.NL);
		out.append("		}").append(CardapioModelBuilderGrid.NL);
		/* Remove the jumbotron's default bottom margin */
		out.append("		.jumbotron {").append(CardapioModelBuilderGrid.NL);
		out.append("			margin-bottom: 0;").append(CardapioModelBuilderGrid.NL);
		out.append("		}").append(CardapioModelBuilderGrid.NL);
		/* Add a gray background color and some padding to the footer */
		out.append("		footer {").append(CardapioModelBuilderGrid.NL);
		out.append("			background-color: #f2f2f2;").append(CardapioModelBuilderGrid.NL);
		out.append("			padding: 25px;").append(CardapioModelBuilderGrid.NL);
		out.append("		}").append(CardapioModelBuilderGrid.NL);
		out.append("	</style>").append(CardapioModelBuilderGrid.NL);
		out.append("</head>").append(CardapioModelBuilderGrid.NL);
	}

}
