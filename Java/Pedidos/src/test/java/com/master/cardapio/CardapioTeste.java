package com.master.cardapio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.master.core.dao.DaoFactory;
import com.master.core.exception.MasterException;
import com.master.pedidos.dao.cardapio.CardapioDao;
import com.master.pedidos.model.cardapio.CardapioModel;
import com.master.pedidos.model.cardapio.CardapioModelBuilder;

public class CardapioTeste {

	@Test
	public void carregaCardapio() {
		CardapioDao dao;
		try {
			dao = DaoFactory.getDao(CardapioDao.class);
			final CardapioModel model = dao.builModel("10596307373059630737305963073784");
			System.out.println(model);
		} catch (final MasterException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void buildCardapio() {
		try {
			final CardapioModelBuilder b = new CardapioModelBuilder();
			final String html = b.build("10596307373059630737305963073784");
			final File file = new File("cardapio.html");
			System.out.println(file.getAbsolutePath());
			final FileOutputStream out = new FileOutputStream(file);
			out.write(html.getBytes());
			out.close();
		} catch (final MasterException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}
