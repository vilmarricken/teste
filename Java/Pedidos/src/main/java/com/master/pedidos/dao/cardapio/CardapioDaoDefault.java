package com.master.pedidos.dao.cardapio;

import com.master.pedidos.model.cardapio.CardapioModel;

public class CardapioDaoDefault implements CardapioDao {
	
	@Override
	public CardapioModel builModel(String estabelecimento) {
		CardapioModel model = new CardapioModel();
		return model;
	}

}
