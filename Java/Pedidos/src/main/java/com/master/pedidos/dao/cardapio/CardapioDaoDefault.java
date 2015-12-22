package com.master.pedidos.dao.cardapio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.master.core.exception.MasterException;
import com.master.pedidos.model.cardapio.Bloco;
import com.master.pedidos.model.cardapio.CardapioModel;
import com.master.pedidos.model.cardapio.Produto;
import com.master.persistence.dao.DaoDefault;

public class CardapioDaoDefault extends DaoDefault implements CardapioDao {

	@Override
	public CardapioModel builModel(String estabelecimento) throws MasterException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = this.prepare(this.sqlCardapioBloco());
			// stmt.setString(1, estabelecimento);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return this.populaCardapio(rs);
			}
			throw new MasterException("Cardápio não encontrado: " + estabelecimento);
		} catch (final SQLException e) {
			throw new MasterException("Erro ao consultart cardapio: " + estabelecimento, e);
		} finally {
			this.closeStatement(stmt);
			this.closeResultSet(rs);
		}
	}

	private static int COLUMN_CARDAPIO_OID = 1;
	private static int COLUMN_CARDAPIO_ATIVO = 2;
	private static int COLUMN_CARDAPIO_TITULO = 3;

	private CardapioModel populaCardapio(ResultSet rs) throws SQLException, MasterException {
		final CardapioModel cardapio = new CardapioModel();
		cardapio.setOid(rs.getString(CardapioDaoDefault.COLUMN_CARDAPIO_OID));
		cardapio.setAtivo(1 == rs.getInt(CardapioDaoDefault.COLUMN_CARDAPIO_ATIVO));
		cardapio.setTitulo(rs.getString(CardapioDaoDefault.COLUMN_CARDAPIO_TITULO));
		final Set<Bloco> blocos = new TreeSet<>();
		cardapio.setBlocos(blocos);
		final Map<String, Bloco> mapBlocos = new HashMap<>();
		this.populateBloco(blocos, mapBlocos, rs);
		return cardapio;
	}

	private static int COLUMN_BLOCO_OID = 4;
	private static int COLUMN_BLOCO_TITULO = 5;
	private static int COLUMN_BLOCO_DESCRICAO = 6;
	private static int COLUMN_BLOCO_IMAGEM_TITULO = 7;
	private static int COLUMN_BLOCO_BACKGROUND = 8;
	private static int COLUMN_BLOCO_ORDEM = 9;
	private static int COLUMN_BLOCO_SUPERIOR = 10;

	private void populateBloco(Set<Bloco> blocos, Map<String, Bloco> mapBlocos, ResultSet rs) throws SQLException, MasterException {
		do {
			final String blocoOid = rs.getString(CardapioDaoDefault.COLUMN_BLOCO_OID);
			final Bloco bloco = this.getBloco(blocoOid, mapBlocos);
			bloco.setTitulo(rs.getString(CardapioDaoDefault.COLUMN_BLOCO_TITULO));
			bloco.setDescricao(rs.getString(CardapioDaoDefault.COLUMN_BLOCO_DESCRICAO));
			bloco.setImagemTitulo(rs.getString(CardapioDaoDefault.COLUMN_BLOCO_IMAGEM_TITULO));
			bloco.setBackground(rs.getString(CardapioDaoDefault.COLUMN_BLOCO_BACKGROUND));
			bloco.setOrdem(rs.getInt(CardapioDaoDefault.COLUMN_BLOCO_ORDEM));
			final String superiorOid = rs.getString(CardapioDaoDefault.COLUMN_BLOCO_SUPERIOR);
			if (superiorOid != null) {
				final Bloco superior = this.getBloco(superiorOid, mapBlocos);
				bloco.setSuperior(superior);
				Set<Bloco> filhos = superior.getFillhos();
				if (filhos == null) {
					filhos = new TreeSet<>();
					superior.setFillhos(filhos);
				}
				filhos.add(bloco);
			} else {
				blocos.add(bloco);
			}
			bloco.setProduto(this.populateProduto(blocoOid));
		} while (rs.next());
	}

	private Set<Produto> populateProduto(String blocoOid) throws MasterException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Set<Produto> produtos = null;
		try {
			stmt = this.prepare(this.sqlProdutoBloco());
			stmt.setString(1, blocoOid);
			rs = stmt.executeQuery();
			if (rs.next()) {
				produtos = new TreeSet<>();
				do {
					final Produto p = new Produto();
					p.setOid(rs.getString(CardapioDaoDefault.COLUMN_PRODUTO_OID));
					final int codigo = rs.getInt(CardapioDaoDefault.COLUMN_PRODUTO_CODIGO);
					if (!rs.wasNull()) {
						p.setCodigo(String.valueOf(codigo));
					}
					p.setDescricao(rs.getString(CardapioDaoDefault.COLUMN_PRODUTO_DESCRICAO));
					p.setImagem(rs.getString(CardapioDaoDefault.COLUMN_PRODUTO_IMAGEM));
					p.setNome(rs.getString(CardapioDaoDefault.COLUMN_PRODUTO_NOME));
					p.setOrdem(rs.getInt(CardapioDaoDefault.COLUMN_PRODUTO_ORDEM));
					p.setTipoProduto(rs.getInt(CardapioDaoDefault.COLUMN_PRODUTO_TIPO_PRODUTO));
					produtos.add(p);
				} while (rs.next());
			}
		} catch (final SQLException e) {
			throw new MasterException("Erro ao consultart produto do bloco: " + blocoOid, e);
		} finally {
			this.closeResultSet(rs);
			this.closeStatement(stmt);
		}
		return produtos;
	}

	private Bloco getBloco(String blocoOid, Map<String, Bloco> mapBlocos) {
		Bloco bloco = mapBlocos.get(blocoOid);
		if (bloco == null) {
			bloco = new Bloco();
			bloco.setOid(blocoOid);
			mapBlocos.put(blocoOid, bloco);
		}
		return bloco;
	}

	private static int COLUMN_PRODUTO_OID = 1;
	private static int COLUMN_PRODUTO_CODIGO = 2;
	private static int COLUMN_PRODUTO_NOME = 3;
	private static int COLUMN_PRODUTO_DESCRICAO = 4;
	private static int COLUMN_PRODUTO_IMAGEM = 5;
	private static int COLUMN_PRODUTO_ATIVO = 6;
	private static int COLUMN_PRODUTO_TIPO_PRODUTO = 7;
	private static int COLUMN_PRODUTO_ORDEM = 8;

	protected String sqlProdutoBloco() {
		final StringBuilder sb = new StringBuilder(128);
		sb.append("select oid,\n");
		sb.append("       codigo,\n");
		sb.append("       nome,\n");
		sb.append("       descricao,\n");
		sb.append("       imagem,\n");
		sb.append("       ativo,\n");
		sb.append("       tipoproduto,\n");
		sb.append("       ordem\n");
		sb.append("  from produto\n");
		sb.append(" inner join produtobloco on produtobloco.produto = produto.oid\n");
		sb.append(" where produtobloco.bloco = ?\n");
		return sb.toString();
	}

	protected String sqlCardapioBloco() {
		final StringBuilder sb = new StringBuilder(128);
		sb.append("select cardapio.oid,\n");
		sb.append("       cardapio.ativo,\n");
		sb.append("       cardapio.titulo,\n");
		sb.append("       bloco.oid,\n");
		sb.append("       bloco.titulo,\n");
		sb.append("       bloco.descricao,\n");
		sb.append("       bloco.imagemtitulo,\n");
		sb.append("       bloco.background,\n");
		sb.append("       bloco.ordem,\n");
		sb.append("       bloco.blocosuperior\n");
		sb.append("  from cardapio\n");
		sb.append("       inner join bloco\n");
		sb.append("                  on bloco.cardapio=cardapio.oid\n");
		// sb.append(" where cardapio.estabelecimento=?\n");
		sb.append("       where cardapio.estabelecimento in (select oid from estabelecimento)\n");
		sb.append("         and cardapio.ativo=1\n");
		return sb.toString();
	}

}
