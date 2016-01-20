package com.master.pedeai.cardapio.builder;

import com.master.core.exception.MasterException;

public interface CardapioModelBuilder {

	byte[] build(String estabelecimento) throws MasterException;

}
