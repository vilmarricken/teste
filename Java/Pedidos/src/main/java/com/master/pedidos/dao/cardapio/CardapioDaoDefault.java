package com.master.pedidos.dao.cardapio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.master.core.exception.MasterException;
import com.master.pedidos.model.cardapio.Bloco;
import com.master.pedidos.model.cardapio.CardapioModel;
import com.master.persistence.dao.DaoDefault;

public class CardapioDaoDefault extends DaoDefault implements CardapioDao {

	@Override
	public CardapioModel builModel(String estabelecimento) throws MasterException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = prepare(sqlCardapioBloco());
			stmt.setString(1, estabelecimento);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return populaCardapio(rs);
			}
			throw new MasterException("Cardápio não encontrado: " + estabelecimento);
		} catch (SQLException e) {
			throw new MasterException("Erro ao consultart cardapio: " + estabelecimento, e);
		} finally {
			closeStatement(stmt);
			closeResultSet(rs);
		}
	}

	private static int COLUMN_CARDAPIO_OID = 1;
	private static int COLUMN_CARDAPIO_ATIVO = 2;
	private static int COLUMN_CARDAPIO_TITULO = 3;

	private CardapioModel populaCardapio(ResultSet rs) throws SQLException {
		CardapioModel cardapio = new CardapioModel();
		cardapio.setOid(rs.getString(COLUMN_CARDAPIO_OID));
		cardapio.setAtivo(1 == rs.getInt(COLUMN_CARDAPIO_ATIVO));
		cardapio.setTitulo(rs.getString(COLUMN_CARDAPIO_TITULO));
		Set<Bloco> blocos = new TreeSet<>();
		cardapio.setBlocos(blocos);
		Map<String, Bloco> mapBlocos = new HashMap<>();
		populateBloco(blocos, mapBlocos, rs);
		return cardapio;
	}

	private static int COLUMN_BLOCO_OID = 4;
	private static int COLUMN_BLOCO_TITULO = 5;
	private static int COLUMN_BLOCO_DESCRICAO = 6;
	private static int COLUMN_BLOCO_IMAGEM_TITULO = 7;
	private static int COLUMN_BLOCO_BACKGROUND = 8;
	private static int COLUMN_BLOCO_ORDEM = 9;
	private static int COLUMN_BLOCO_SUPERIOR = 10;

	private void populateBloco(Set<Bloco> blocos, Map<String, Bloco> mapBlocos, ResultSet rs) throws SQLException {
		Bloco bloco = getBloco(rs.getString(COLUMN_BLOCO_OID), mapBlocos);
		bloco.setTitulo(rs.getString(COLUMN_BLOCO_TITULO));
		bloco.setDescricao(rs.getString(COLUMN_BLOCO_DESCRICAO));
		bloco.setImagemTitulo(rs.getString(COLUMN_BLOCO_IMAGEM_TITULO));
		bloco.setBackground(rs.getString(COLUMN_BLOCO_BACKGROUND));
		bloco.setOrdem(rs.getInt(COLUMN_BLOCO_ORDEM));
		String superiorOid = rs.getString(COLUMN_BLOCO_SUPERIOR);
		if (rs.wasNull()) {
			Bloco superior = getBloco(superiorOid, mapBlocos);
			bloco.setSuperior(superior);
			Set<Bloco> filhos = superior.getFillhos();
			if(filhos == null){
				filhos = new TreeSet<>();
				superior.setFillhos(filhos);
			}
			filhos.add(bloco);
		}
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

	protected String sqlCardapioBloco() {
		StringBuilder sb = new StringBuilder(128);
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
		sb.append("       where cardapio.estabelecimento=?\n");
		sb.append("       where cardapio.ativo=1\n");
		return sb.toString();
	}

}
