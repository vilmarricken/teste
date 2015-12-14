package com.master.pedidos.dao.cardapio;

import java.sql.PreparedStatement;

import com.master.core.exception.MasterException;
import com.master.pedidos.model.cardapio.CardapioModel;
import com.master.persistence.dao.DaoDefault;

public class CardapioDaoDefault extends DaoDefault implements CardapioDao {

	@Override
	public CardapioModel builModel(String estabelecimento) throws MasterException {
		CardapioModel model = new CardapioModel();
		PreparedStatement stmt = prepare(sqlCardapioBloco());
		stmt.setString(1, estabelecimento);

		return model;
	}

	protected String sqlCardapioBloco() {
		StringBuilder sb = new StringBuilder(128);
		sb.append("select cardapio.oid as cardapio_oid,\n");
		sb.append("       cardapio.ativo as cardapio_ativo,\n");
		sb.append("       cardapio.titulo as cardapio_titulo,\n");
		sb.append("       bloco.oid as bloco_oid,\n");
		sb.append("       bloco.titulo as bloco_titulo,\n");
		sb.append("       bloco.descricao as bloco_descricao,\n");
		sb.append("       bloco.imagemtitulo as bloco_imagetitulo,\n");
		sb.append("       bloco.background	as bloco_background,\n");
		sb.append("       bloco.ordem as bloco_ordem,\n");
		sb.append("       bloco.cardapio as bloco_cardapio,\n");
		sb.append("       bloco.blocosuperior blocosuperior\n");
		sb.append("  from cardapio\n");
		sb.append("       inner join bloco\n");
		sb.append("                  on bloco.cardapio=cardapio.oid\n");
		sb.append("       where cardapio.estabelecimento=?\n");
		sb.append("       where cardapio.ativo=1\n");
		return sb.toString();
	}

}
