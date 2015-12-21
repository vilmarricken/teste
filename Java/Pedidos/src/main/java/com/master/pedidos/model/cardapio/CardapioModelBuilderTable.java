package com.master.pedidos.model.cardapio;

import java.text.NumberFormat;
import java.util.Set;

import com.master.core.dao.DaoFactory;
import com.master.core.exception.MasterException;
import com.master.pedidos.dao.cardapio.CardapioDao;

public class CardapioModelBuilderTable {

	private static final String NL = "\n";

	public String build(String estabelecimento) throws MasterException {
		final CardapioDao dao = DaoFactory.getDao(CardapioDao.class);
		final CardapioModel model = dao.builModel(estabelecimento);
		final StringBuilder out = new StringBuilder(5 * 1024);
		this.build(model, out);
		return out.toString();
	}

	private void build(CardapioModel model, StringBuilder out) {
		out.append("<!DOCTYPE html>").append(CardapioModelBuilderTable.NL);
		out.append("<html lang=\"pt\">").append(CardapioModelBuilderTable.NL);
		this.buildHeader(model, out);
		this.buildBody(model, out);
		out.append("</html>").append(CardapioModelBuilderTable.NL);
	}

	private void buildBody(CardapioModel model, StringBuilder out) {
		out.append("<body>").append(CardapioModelBuilderTable.NL);
		this.buildBodyHeader(model, out);
		this.buildBodyNavigation(model, out);
		this.builBodyContainer(model, out);
		out.append("</body>").append(CardapioModelBuilderTable.NL);
	}

	private void builBodyContainer(CardapioModel model, StringBuilder out) {
		out.append("	<div class=\"container\">").append(CardapioModelBuilderTable.NL);
		// out.append(" <ul
		// class=\"list-group\">").append(CardapioModelBuilder.NL);
		final Set<Bloco> blocos = model.getBlocos();
		for (final Bloco bloco : blocos) {
			final Set<Bloco> filhos = bloco.getFillhos();
			for (final Bloco filho : filhos) {
				// out.append(" <li
				// class=\"list-group-item\">").append(CardapioModelBuilder.NL);
				out.append("	<div class=\"panel panel-default\">").append(CardapioModelBuilderTable.NL);
				this.buildBodyBloco(filho, out);
				out.append("	</div>").append(CardapioModelBuilderTable.NL);
				// out.append(" </li>").append(CardapioModelBuilder.NL);
			}
		}
		// out.append(" </ul>").append(CardapioModelBuilder.NL);
		out.append("	</div>").append(CardapioModelBuilderTable.NL);
	}

	private void buildBodyBloco(Bloco bloco, StringBuilder out) {
		out.append("	<div class=\"panel-body\">").append(CardapioModelBuilderTable.NL);
		out.append(bloco.getTitulo()).append(CardapioModelBuilderTable.NL);
		out.append("	</div>").append(CardapioModelBuilderTable.NL);
		out.append("<table class=\"table table-striped\">").append(CardapioModelBuilderTable.NL);
		// out.append("<tr>").append(CardapioModelBuilder.NL);
		// out.append(" <td colspan=\"4\"
		// align=\"center\">").append(bloco.getTitulo()).append("</td>").append(CardapioModelBuilder.NL);
		// out.append("</tr>").append(CardapioModelBuilder.NL);
		final Set<Produto> produtos = bloco.getProduto();
		if (produtos != null) {
			for (final Produto produto : produtos) {
				this.buildBodyProduto(produto, out);
			}
		}
		out.append("</table>").append(CardapioModelBuilderTable.NL);
		// out.append("</br></br>").append(CardapioModelBuilder.NL);
	}

	private void buildBodyProduto(Produto produto, StringBuilder out) {
		out.append("<tr>").append(CardapioModelBuilderTable.NL);
		/*
		 * out.append("<td width=\"10%\">"
		 * ).append(produto.getCodigo()).append("</td>").append(
		 * CardapioModelBuilder.NL); out.append("<td width=\"55%\">"
		 * ).append(produto.getNome()).append("</td>").append(
		 * CardapioModelBuilder.NL); out.append(
		 * "<td width=\"20%\" nowrap=\"true\">"
		 * ).append(NumberFormat.getCurrencyInstance().format(10)).append(
		 * "</td>").append(CardapioModelBuilder.NL); out.append(
		 * "<td width=\"10%\"><input type=\"text\" value=\"1\" maxwidth=\"2\" size=\"2\"></td>"
		 * ).append(CardapioModelBuilder.NL); out.append(
		 * "<td width=\"5%\"><span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span></td>"
		 * ).append(CardapioModelBuilder.NL);
		 */
		out.append("<td>").append(produto.getCodigo()).append("</td>").append(CardapioModelBuilderTable.NL);
		out.append("<td>").append(produto.getNome()).append("</td>").append(CardapioModelBuilderTable.NL);
		out.append("<td nowrap=\"true\">").append(NumberFormat.getCurrencyInstance().format(10)).append("</td>").append(CardapioModelBuilderTable.NL);
		out.append("<td><input type=\"text\" value=\"1\" maxwidth=\"2\" size=\"2\"></td>").append(CardapioModelBuilderTable.NL);
		out.append("<td><span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span></td>").append(CardapioModelBuilderTable.NL);
		out.append("</tr>").append(CardapioModelBuilderTable.NL);
		out.append("<tr>").append(CardapioModelBuilderTable.NL);
		final String descricao = produto.getDescricao();
		if (descricao != null) {
			out.append("	<td colspan=\"4\">").append(descricao).append("</td>").append(CardapioModelBuilderTable.NL);
		}
		out.append("</tr>").append(CardapioModelBuilderTable.NL);
	}

	private void buildBodyNavigation(CardapioModel model, StringBuilder out) {
		out.append("	<nav class=\"navbar navbar-inverse\">").append(CardapioModelBuilderTable.NL);
		out.append("		<div class=\"container-fluid\">").append(CardapioModelBuilderTable.NL);
		out.append("			<div class=\"navbar-header\">").append(CardapioModelBuilderTable.NL);
		out.append("				<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#myNavbar\">").append(CardapioModelBuilderTable.NL);
		out.append("					<span class=\"icon-bar\"></span>").append(CardapioModelBuilderTable.NL);
		out.append("					<span class=\"icon-bar\"></span>").append(CardapioModelBuilderTable.NL);
		out.append("					<span class=\"icon-bar\"></span>").append(CardapioModelBuilderTable.NL);
		out.append("				</button>").append(CardapioModelBuilderTable.NL);
		out.append("				<a class=\"navbar-brand\" href=\"#\">Logo</a>").append(CardapioModelBuilderTable.NL);
		out.append("			</div>").append(CardapioModelBuilderTable.NL);
		out.append("			<div class=\"collapse navbar-collapse\" id=\"myNavbar\">").append(CardapioModelBuilderTable.NL);
		out.append("				<ul class=\"nav navbar-nav\">").append(CardapioModelBuilderTable.NL);
		out.append("					<li class=\"active\"><a href=\"#\">Home</a></li>").append(CardapioModelBuilderTable.NL);
		out.append("					<li><a href=\"#\">Products</a></li>").append(CardapioModelBuilderTable.NL);
		out.append("					<li><a href=\"#\">Deals</a></li>").append(CardapioModelBuilderTable.NL);
		out.append("					<li><a href=\"#\">Stores</a></li>").append(CardapioModelBuilderTable.NL);
		out.append("					<li><a href=\"#\">Contact</a></li>").append(CardapioModelBuilderTable.NL);
		out.append("				</ul>").append(CardapioModelBuilderTable.NL);
		out.append("				<ul class=\"nav navbar-nav navbar-right\">").append(CardapioModelBuilderTable.NL);
		out.append("					<li><a href=\"#\"><span class=\"glyphicon glyphicon-user\"></span> Your Account</a></li>").append(CardapioModelBuilderTable.NL);
		out.append("					<li><a href=\"#\"><span class=\"glyphicon glyphicon-shopping-cart\"></span> Cart</a></li>").append(CardapioModelBuilderTable.NL);
		out.append("				</ul>").append(CardapioModelBuilderTable.NL);
		out.append("			</div>").append(CardapioModelBuilderTable.NL);
		out.append("		</div>").append(CardapioModelBuilderTable.NL);
		out.append("	</nav>").append(CardapioModelBuilderTable.NL);
	}

	private void buildBodyHeader(CardapioModel model, StringBuilder out) {
		out.append("	<div class=\"jumbotron\">").append(CardapioModelBuilderTable.NL);
		out.append("		<div class=\"container text-center\">").append(CardapioModelBuilderTable.NL);
		out.append("			<h1>");
		out.append(model.getTitulo());
		out.append("</h1>").append(CardapioModelBuilderTable.NL);
		final String descricao = model.getDescricao();
		if (descricao != null) {
			out.append("			<p>");
			out.append(descricao);
			out.append("</p>").append(CardapioModelBuilderTable.NL);
		}
		out.append("		</div>").append(CardapioModelBuilderTable.NL);
		out.append("	</div>").append(CardapioModelBuilderTable.NL);
	}

	private void buildHeader(CardapioModel model, StringBuilder out) {
		out.append("<head>").append(CardapioModelBuilderTable.NL);
		out.append("	<title>Bootstrap Example</title>").append(CardapioModelBuilderTable.NL);
		out.append("	<meta charset=\"iso-8859-1\">").append(CardapioModelBuilderTable.NL);
		out.append("	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">").append(CardapioModelBuilderTable.NL);
		out.append("	<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">").append(CardapioModelBuilderTable.NL);
		out.append("	<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>").append(CardapioModelBuilderTable.NL);
		out.append("	<script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>").append(CardapioModelBuilderTable.NL);
		out.append("	<style>").append(CardapioModelBuilderTable.NL);
		/*
		 * Remove the navbar's default rounded borders and increase the bottom
		 * margin
		 */
		out.append("		.navbar {").append(CardapioModelBuilderTable.NL);
		out.append("			margin-bottom: 50px;").append(CardapioModelBuilderTable.NL);
		out.append("			border-radius: 0;").append(CardapioModelBuilderTable.NL);
		out.append("		}").append(CardapioModelBuilderTable.NL);
		/* Remove the jumbotron's default bottom margin */
		out.append("		.jumbotron {").append(CardapioModelBuilderTable.NL);
		out.append("			margin-bottom: 0;").append(CardapioModelBuilderTable.NL);
		out.append("		}").append(CardapioModelBuilderTable.NL);
		/* Add a gray background color and some padding to the footer */
		out.append("		footer {").append(CardapioModelBuilderTable.NL);
		out.append("			background-color: #f2f2f2;").append(CardapioModelBuilderTable.NL);
		out.append("			padding: 25px;").append(CardapioModelBuilderTable.NL);
		out.append("		}").append(CardapioModelBuilderTable.NL);
		out.append("	</style>").append(CardapioModelBuilderTable.NL);
		out.append("</head>").append(CardapioModelBuilderTable.NL);
	}

}
