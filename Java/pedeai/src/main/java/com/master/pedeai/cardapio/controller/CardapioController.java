package com.master.pedeai.cardapio.controller;

import java.io.File;
import java.util.Map;

public abstract class CardapioController {

	private static CardapioController INSTANCE;

	private Map<String, File> cardapio;

	public static CardapioController getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CardapioControllerImpl();
		}
		return INSTANCE;
	}

}
