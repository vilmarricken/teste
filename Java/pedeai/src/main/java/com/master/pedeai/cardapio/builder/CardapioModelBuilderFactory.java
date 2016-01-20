package com.master.pedeai.cardapio.builder;

public class CardapioModelBuilderFactory {

	private static CardapioModelBuilderFactory FACTORY = new CardapioModelBuilderFactory();

	private CardapioModelBuilder builder = new CardapioModelBuilderImpl();
	
	public static CardapioModelBuilderFactory getInstance() {
		return FACTORY;
	}
	
	public CardapioModelBuilder getBuilder() {
		return builder;
	}

}
