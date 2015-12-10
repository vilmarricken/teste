package com.master.pedidos.model.cardapio;

import com.master.core.dao.DaoFactory;
import com.master.core.exception.MasterException;
import com.master.pedidos.dao.cardapio.CardapioDao;

public class CardapioModelBuilder {

	public static CardapioModel builder(String estabelecimento) throws MasterException {
		CardapioDao dao = DaoFactory.getDao(CardapioDao.class);
		CardapioModel model = dao.builModel(estabelecimento);
		return model;
	}
	
}
