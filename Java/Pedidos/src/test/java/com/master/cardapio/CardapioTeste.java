package com.master.cardapio;

import org.junit.Test;

import com.master.core.dao.DaoFactory;
import com.master.core.exception.MasterException;
import com.master.pedidos.dao.cardapio.CardapioDao;
import com.master.pedidos.model.cardapio.CardapioModel;

public class CardapioTeste {

	@Test
	public void carregaCardapio() {
		CardapioDao dao;
		try {
			dao = DaoFactory.getDao(CardapioDao.class);
			CardapioModel model = dao.builModel("10596307373059630737305963073784");
			System.out.println(model);
		} catch (MasterException e) {
			e.printStackTrace();
		}
	}

}
