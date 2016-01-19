package com.master.pedeai.cardapio.builder;

import java.util.Set;

import com.master.core.dao.DaoFactory;
import com.master.core.exception.MasterException;
import com.master.pedeai.bloco.model.BlocoModel;
import com.master.pedeai.cardapio.dao.CardapioDao;
import com.master.pedeai.cardapio.model.CardapioModel;
import com.master.pedeai.produto.model.Produto;

public class CardapioModelBuilderGrid2 {

	private static final String NL = "\n";

	public String build(String estabelecimento) throws MasterException {
		final CardapioDao dao = DaoFactory.getDao(CardapioDao.class);
		final CardapioModel model = dao.builModel(estabelecimento);
		final StringBuilder out = new StringBuilder(5 * 1024);
		this.build(model, out);
		return out.toString();
	}

	private void build(CardapioModel model, StringBuilder out) {
		out.append("<!DOCTYPE html>").append(CardapioModelBuilderGrid2.NL);
		out.append("<html lang=\"pt\">").append(CardapioModelBuilderGrid2.NL);
		this.buildHeader(model, out);
		this.buildBody(model, out);
		out.append("</html>").append(CardapioModelBuilderGrid2.NL);
	}

	private void buildBody(CardapioModel model, StringBuilder out) {
		out.append("<body>").append(CardapioModelBuilderGrid2.NL);
		this.buildBodyHeader(model, out);
		this.buildBodyNavigation(model, out);
		this.builBodyContainer(model, out);
		out.append("</body>").append(CardapioModelBuilderGrid2.NL);
	}

	private void builBodyContainer(CardapioModel model, StringBuilder out) {
		out.append("	<div class=\"container\">").append(CardapioModelBuilderGrid2.NL);
		final Set<BlocoModel> blocos = model.getBlocos();
		for (final BlocoModel bloco : blocos) {
			this.buildBodyBloco(bloco, out);
		}
		out.append("	</div>").append(CardapioModelBuilderGrid2.NL);
	}

	private void buildBodyBloco(BlocoModel bloco, StringBuilder out) {
		out.append("	<div class=\"panel panel-default\">").append(CardapioModelBuilderGrid2.NL);
		out.append("		<div class=\"panel-heading\">").append(bloco.getTitulo()).append("	</div>").append(CardapioModelBuilderGrid2.NL);
		//out.append("	<div class=\"panel-title\">").append(bloco.getTitulo()).append("	</div>").append(CardapioModelBuilder.NL);
		out.append("		<div class=\"panel-body\">").append(CardapioModelBuilderGrid2.NL);
		final Set<Produto> produtos = bloco.getProduto();
		if (produtos != null) {
			this.buildBodyProduto(produtos, out);
		}
		out.append("		</div>").append(CardapioModelBuilderGrid2.NL);
		out.append("	</div>").append(CardapioModelBuilderGrid2.NL);
	}

	private void buildBodyProduto(Set<Produto> produtos, StringBuilder out) {
		Produto[] array = new Produto[produtos.size()];
		produtos.toArray(array);
		int columns = 2;
		for (int i = 0; i < array.length; i = i + columns) {
			out.append("			<div class=\"row\">").append(CardapioModelBuilderGrid2.NL);
			int startColumn = i * columns;
			for (int j = startColumn; j < i * columns + columns && j < array.length; j++) {
				out.append("				<div class=\"col-xs-6\">").append(CardapioModelBuilderGrid2.NL);
				this.buildBodyProduto(array[j], out);
				out.append("				</div>").append(CardapioModelBuilderGrid2.NL);
			}
			out.append("			</div>").append(CardapioModelBuilderGrid2.NL);
		}
	}

	private void buildBodyProduto(Produto produto, StringBuilder out) {
		out.append("	<div class=\"panel panel-default\">").append(CardapioModelBuilderGrid2.NL);
		out.append("<div class=\"row\">").append(CardapioModelBuilderGrid2.NL);
		out.append("	<div class=\"col-xs-1\">").append(produto.getCodigo()).append("	</div>").append(CardapioModelBuilderGrid2.NL);
		out.append("	<div class=\"col-xs-11\">").append(produto.getNome()).append("	</div>").append(CardapioModelBuilderGrid2.NL);
		out.append("</div>").append(CardapioModelBuilderGrid2.NL);
		out.append("<div class=\"row\">").append(CardapioModelBuilderGrid2.NL);
		out.append("	<div class=\"col-xs-12\">").append(produto.getDescricao()).append("	</div>").append(CardapioModelBuilderGrid2.NL);
		out.append("</div>").append(CardapioModelBuilderGrid2.NL);
		out.append("			</div>").append(CardapioModelBuilderGrid2.NL);
		/*
		out.append("<tr>").append(CardapioModelBuilder.NL);
		out.append("<td>").append("</td>").append(CardapioModelBuilder.NL);
		out.append("<td>").append(produto.getNome()).append("</td>").append(CardapioModelBuilder.NL);
		out.append("<td nowrap=\"true\">").append(NumberFormat.getCurrencyInstance().format(10)).append("</td>").append(CardapioModelBuilder.NL);
		out.append("<td><input type=\"text\" value=\"1\" maxwidth=\"2\" size=\"2\"></td>").append(CardapioModelBuilder.NL);
		out.append("<td><span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span></td>").append(CardapioModelBuilder.NL);
		out.append("</tr>").append(CardapioModelBuilder.NL);
		out.append("<tr>").append(CardapioModelBuilder.NL);
		final String descricao = produto.getDescricao();
		if (descricao != null) {
			out.append("	<td colspan=\"4\">").append(descricao).append("</td>").append(CardapioModelBuilder.NL);
		}
		out.append("</tr>").append(CardapioModelBuilder.NL);
		*/
	}

	private void buildBodyNavigation(CardapioModel model, StringBuilder out) {
		out.append("	<nav class=\"navbar navbar-inverse\">").append(CardapioModelBuilderGrid2.NL);
		out.append("		<div class=\"container-fluid\">").append(CardapioModelBuilderGrid2.NL);
		out.append("			<div class=\"navbar-header\">").append(CardapioModelBuilderGrid2.NL);
		out.append("				<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#myNavbar\">").append(CardapioModelBuilderGrid2.NL);
		out.append("					<span class=\"icon-bar\"></span>").append(CardapioModelBuilderGrid2.NL);
		out.append("					<span class=\"icon-bar\"></span>").append(CardapioModelBuilderGrid2.NL);
		out.append("					<span class=\"icon-bar\"></span>").append(CardapioModelBuilderGrid2.NL);
		out.append("				</button>").append(CardapioModelBuilderGrid2.NL);
		out.append("				<a class=\"navbar-brand\" href=\"#\">Logo</a>").append(CardapioModelBuilderGrid2.NL);
		out.append("			</div>").append(CardapioModelBuilderGrid2.NL);
		out.append("			<div class=\"collapse navbar-collapse\" id=\"myNavbar\">").append(CardapioModelBuilderGrid2.NL);
		out.append("				<ul class=\"nav navbar-nav\">").append(CardapioModelBuilderGrid2.NL);
		out.append("					<li class=\"active\"><a href=\"#\">Home</a></li>").append(CardapioModelBuilderGrid2.NL);
		out.append("					<li><a href=\"#\">Products</a></li>").append(CardapioModelBuilderGrid2.NL);
		out.append("					<li><a href=\"#\">Deals</a></li>").append(CardapioModelBuilderGrid2.NL);
		out.append("					<li><a href=\"#\">Stores</a></li>").append(CardapioModelBuilderGrid2.NL);
		out.append("					<li><a href=\"#\">Contact</a></li>").append(CardapioModelBuilderGrid2.NL);
		out.append("				</ul>").append(CardapioModelBuilderGrid2.NL);
		out.append("				<ul class=\"nav navbar-nav navbar-right\">").append(CardapioModelBuilderGrid2.NL);
		out.append("					<li><a href=\"#\"><span class=\"glyphicon glyphicon-user\"></span> Your Account</a></li>").append(CardapioModelBuilderGrid2.NL);
		out.append("					<li><a href=\"#\"><span class=\"glyphicon glyphicon-shopping-cart\"></span> Cart</a></li>").append(CardapioModelBuilderGrid2.NL);
		out.append("				</ul>").append(CardapioModelBuilderGrid2.NL);
		out.append("			</div>").append(CardapioModelBuilderGrid2.NL);
		out.append("		</div>").append(CardapioModelBuilderGrid2.NL);
		out.append("	</nav>").append(CardapioModelBuilderGrid2.NL);
	}

	private void buildBodyHeader(CardapioModel model, StringBuilder out) {
		out.append("	<div class=\"jumbotron\">").append(CardapioModelBuilderGrid2.NL);
		out.append("		<div class=\"container text-center\">").append(CardapioModelBuilderGrid2.NL);
		out.append("			<h1>");
		out.append(model.getTitulo());
		out.append("</h1>").append(CardapioModelBuilderGrid2.NL);
		final String descricao = model.getDescricao();
		if (descricao != null) {
			out.append("			<p>");
			out.append(descricao);
			out.append("</p>").append(CardapioModelBuilderGrid2.NL);
		}
		out.append("		</div>").append(CardapioModelBuilderGrid2.NL);
		out.append("	</div>").append(CardapioModelBuilderGrid2.NL);
	}

	private void buildHeader(CardapioModel model, StringBuilder out) {
		out.append("<head>").append(CardapioModelBuilderGrid2.NL);
		out.append("	<title>Bootstrap Example</title>").append(CardapioModelBuilderGrid2.NL);
		out.append("	<meta charset=\"iso-8859-1\">").append(CardapioModelBuilderGrid2.NL);
		out.append("	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">").append(CardapioModelBuilderGrid2.NL);
		out.append("	<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">").append(CardapioModelBuilderGrid2.NL);
		out.append("	<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>").append(CardapioModelBuilderGrid2.NL);
		out.append("	<script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>").append(CardapioModelBuilderGrid2.NL);
		out.append("	<style>").append(CardapioModelBuilderGrid2.NL);
		/*
		 * Remove the navbar's default rounded borders and increase the bottom
		 * margin
		 */
		out.append("		.navbar {").append(CardapioModelBuilderGrid2.NL);
		out.append("			margin-bottom: 50px;").append(CardapioModelBuilderGrid2.NL);
		out.append("			border-radius: 0;").append(CardapioModelBuilderGrid2.NL);
		out.append("		}").append(CardapioModelBuilderGrid2.NL);
		/* Remove the jumbotron's default bottom margin */
		out.append("		.jumbotron {").append(CardapioModelBuilderGrid2.NL);
		out.append("			margin-bottom: 0;").append(CardapioModelBuilderGrid2.NL);
		out.append("		}").append(CardapioModelBuilderGrid2.NL);
		/* Add a gray background color and some padding to the footer */
		out.append("		footer {").append(CardapioModelBuilderGrid2.NL);
		out.append("			background-color: #f2f2f2;").append(CardapioModelBuilderGrid2.NL);
		out.append("			padding: 25px;").append(CardapioModelBuilderGrid2.NL);
		out.append("		}").append(CardapioModelBuilderGrid2.NL);
		out.append("	</style>").append(CardapioModelBuilderGrid2.NL);
		out.append("</head>").append(CardapioModelBuilderGrid2.NL);
	}

}
