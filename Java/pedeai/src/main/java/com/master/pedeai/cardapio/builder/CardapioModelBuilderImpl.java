package com.master.pedeai.cardapio.builder;

import java.text.NumberFormat;
import java.util.Set;

import com.master.core.dao.DaoFactory;
import com.master.core.exception.MasterException;
import com.master.pedeai.bloco.model.BlocoModel;
import com.master.pedeai.cardapio.dao.CardapioDao;
import com.master.pedeai.cardapio.model.CardapioModel;
import com.master.pedeai.produto.model.Produto;

public class CardapioModelBuilderImpl {

	private static final String NL = "\n";

	public String build(String estabelecimento) throws MasterException {
		final CardapioDao dao = DaoFactory.getDao(CardapioDao.class);
		final CardapioModel model = dao.builModel(estabelecimento);
		final StringBuilder out = new StringBuilder(5 * 1024);
		this.build(model, out);
		return out.toString();
	}

	private void build(CardapioModel model, StringBuilder out) {
		out.append("<!DOCTYPE html>").append(CardapioModelBuilderImpl.NL);
		out.append("<html lang=\"pt\">").append(CardapioModelBuilderImpl.NL);
		this.buildHeader(model, out);
		this.buildBody(model, out);
		out.append("</html>").append(CardapioModelBuilderImpl.NL);
	}

	private void buildBody(CardapioModel model, StringBuilder out) {
		out.append("<body>").append(CardapioModelBuilderImpl.NL);
		this.buildBodyHeader(model, out);
		this.buildBodyNavigation(model, out);
		this.builBodyContainer(model, out);
		out.append("</body>").append(CardapioModelBuilderImpl.NL);
	}

	private void builBodyContainer(CardapioModel model, StringBuilder out) {
		out.append("	<div class=\"container\">").append(CardapioModelBuilderImpl.NL);
		final Set<BlocoModel> blocos = model.getBlocos();
		for (final BlocoModel bloco : blocos) {
			this.buildBodyBloco(bloco, out);
		}
		out.append("	</div>").append(CardapioModelBuilderImpl.NL);
	}

	private void buildBodyBloco(BlocoModel bloco, StringBuilder out) {
		out.append("	<div class=\"panel panel-default\">").append(CardapioModelBuilderImpl.NL);
		out.append("		<div class=\"panel-heading\">").append(bloco.getTitulo()).append("	</div>").append(CardapioModelBuilderImpl.NL);
		out.append("		<div class=\"panel-body\">").append(CardapioModelBuilderImpl.NL);
		final Set<Produto> produtos = bloco.getProduto();
		if (produtos != null) {
			this.buildBodyProduto(produtos, out);
		}
		out.append("		</div>").append(CardapioModelBuilderImpl.NL);
		out.append("	</div>").append(CardapioModelBuilderImpl.NL);
	}

	private void buildBodyProduto(Set<Produto> produtos, StringBuilder out) {
		final Produto[] array = new Produto[produtos.size()];
		produtos.toArray(array);
		final int columns = 2;
		for (int i = 0; i < array.length; i = i + columns) {
			out.append("			<table class=\"table\">").append(CardapioModelBuilderImpl.NL);
			final int startColumn = i * columns;
			for (int j = startColumn; j < i * columns + columns && j < array.length; j++) {
				this.buildBodyProduto(array[j], out);
			}
			out.append("			</table>").append(CardapioModelBuilderImpl.NL);
		}
	}

	private void buildBodyProduto(Produto produto, StringBuilder out) {
		out.append("<tr>").append(CardapioModelBuilderImpl.NL);
		out.append("<td width=\"5%\">").append(produto.getCodigo()).append("</td>").append(CardapioModelBuilderImpl.NL);
		out.append("<td width=\"80%\">").append(produto.getNome()).append("</td>").append(CardapioModelBuilderImpl.NL);
		out.append("<td width=\"5%\" nowrap=\"true\">").append(NumberFormat.getCurrencyInstance().format(10)).append("</td>").append(CardapioModelBuilderImpl.NL);
		out.append("<td width=\"5%\"><input type=\"text\" value=\"1\" maxwidth=\"2\" size=\"2\"></td>").append(CardapioModelBuilderImpl.NL);
		out.append("<td width=\"5%\"><span class=\"glyphicon glyphicon-edit\" aria-hidden=\"true\" onclick=\"alert(1)\" style=\"cursor: pointer;\"></span></td>").append(CardapioModelBuilderImpl.NL);
		out.append("<td width=\"5%\"><span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\" onclick=\"alert(2)\" style=\"cursor: pointer;\"></span></td>").append(CardapioModelBuilderImpl.NL);
		out.append("</tr>").append(CardapioModelBuilderImpl.NL);
		out.append("<tr>").append(CardapioModelBuilderImpl.NL);
		final String descricao = produto.getDescricao();
		if (descricao != null) {
			out.append("	<td colspan=\"6\">").append(descricao).append("</td>").append(CardapioModelBuilderImpl.NL);
		}
		out.append("</tr>").append(CardapioModelBuilderImpl.NL);
	}

	private void buildBodyNavigation(CardapioModel model, StringBuilder out) {
		out.append("	<nav class=\"navbar navbar-inverse\">").append(CardapioModelBuilderImpl.NL);
		out.append("		<div class=\"container-fluid\">").append(CardapioModelBuilderImpl.NL);
		out.append("			<div class=\"navbar-header\">").append(CardapioModelBuilderImpl.NL);
		out.append("				<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#myNavbar\">").append(CardapioModelBuilderImpl.NL);
		out.append("					<span class=\"icon-bar\"></span>").append(CardapioModelBuilderImpl.NL);
		out.append("					<span class=\"icon-bar\"></span>").append(CardapioModelBuilderImpl.NL);
		out.append("					<span class=\"icon-bar\"></span>").append(CardapioModelBuilderImpl.NL);
		out.append("				</button>").append(CardapioModelBuilderImpl.NL);
		out.append("				<a class=\"navbar-brand\" href=\"#\">Logo</a>").append(CardapioModelBuilderImpl.NL);
		out.append("			</div>").append(CardapioModelBuilderImpl.NL);
		out.append("			<div class=\"collapse navbar-collapse\" id=\"myNavbar\">").append(CardapioModelBuilderImpl.NL);
		out.append("				<ul class=\"nav navbar-nav\">").append(CardapioModelBuilderImpl.NL);
		out.append("					<li class=\"active\"><a href=\"#\">Home</a></li>").append(CardapioModelBuilderImpl.NL);
		out.append("					<li><a href=\"#\">Products</a></li>").append(CardapioModelBuilderImpl.NL);
		out.append("					<li><a href=\"#\">Deals</a></li>").append(CardapioModelBuilderImpl.NL);
		out.append("					<li><a href=\"#\">Stores</a></li>").append(CardapioModelBuilderImpl.NL);
		out.append("					<li><a href=\"#\">Contact</a></li>").append(CardapioModelBuilderImpl.NL);
		out.append("				</ul>").append(CardapioModelBuilderImpl.NL);
		out.append("				<ul class=\"nav navbar-nav navbar-right\">").append(CardapioModelBuilderImpl.NL);
		out.append("					<li><a href=\"#\"><span class=\"glyphicon glyphicon-user\"></span> Your Account</a></li>").append(CardapioModelBuilderImpl.NL);
		out.append("					<li><a href=\"#\"><span class=\"glyphicon glyphicon-shopping-cart\"></span> Cart</a></li>").append(CardapioModelBuilderImpl.NL);
		out.append("				</ul>").append(CardapioModelBuilderImpl.NL);
		out.append("			</div>").append(CardapioModelBuilderImpl.NL);
		out.append("		</div>").append(CardapioModelBuilderImpl.NL);
		out.append("	</nav>").append(CardapioModelBuilderImpl.NL);
	}

	private void buildBodyHeader(CardapioModel model, StringBuilder out) {
		out.append("	<div class=\"jumbotron\">").append(CardapioModelBuilderImpl.NL);
		out.append("		<div class=\"container text-center\">").append(CardapioModelBuilderImpl.NL);
		out.append("			<h1>");
		out.append(model.getTitulo());
		out.append("</h1>").append(CardapioModelBuilderImpl.NL);
		final String descricao = model.getDescricao();
		if (descricao != null) {
			out.append("			<p>");
			out.append(descricao);
			out.append("</p>").append(CardapioModelBuilderImpl.NL);
		}
		out.append("		</div>").append(CardapioModelBuilderImpl.NL);
		out.append("	</div>").append(CardapioModelBuilderImpl.NL);
	}

	private void buildHeader(CardapioModel model, StringBuilder out) {
		out.append("<head>").append(CardapioModelBuilderImpl.NL);
		out.append("	<title>Bootstrap Example</title>").append(CardapioModelBuilderImpl.NL);
		out.append("	<meta charset=\"iso-8859-1\">").append(CardapioModelBuilderImpl.NL);
		out.append("	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">").append(CardapioModelBuilderImpl.NL);
		out.append("	<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">").append(CardapioModelBuilderImpl.NL);
		out.append("	<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>").append(CardapioModelBuilderImpl.NL);
		out.append("	<script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>").append(CardapioModelBuilderImpl.NL);
		out.append("	<style>").append(CardapioModelBuilderImpl.NL);
		/*
		 * Remove the navbar's default rounded borders and increase the bottom
		 * margin
		 */
		out.append("		.navbar {").append(CardapioModelBuilderImpl.NL);
		out.append("			margin-bottom: 50px;").append(CardapioModelBuilderImpl.NL);
		out.append("			border-radius: 0;").append(CardapioModelBuilderImpl.NL);
		out.append("		}").append(CardapioModelBuilderImpl.NL);
		/* Remove the jumbotron's default bottom margin */
		out.append("		.jumbotron {").append(CardapioModelBuilderImpl.NL);
		out.append("			margin-bottom: 0;").append(CardapioModelBuilderImpl.NL);
		out.append("		}").append(CardapioModelBuilderImpl.NL);
		/* Add a gray background color and some padding to the footer */
		out.append("		footer {").append(CardapioModelBuilderImpl.NL);
		out.append("			background-color: #f2f2f2;").append(CardapioModelBuilderImpl.NL);
		out.append("			padding: 25px;").append(CardapioModelBuilderImpl.NL);
		out.append("		}").append(CardapioModelBuilderImpl.NL);
		out.append("	</style>").append(CardapioModelBuilderImpl.NL);
		out.append("</head>").append(CardapioModelBuilderImpl.NL);
	}

}
