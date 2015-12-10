package com.master.pedidos.dao.cardapio;

import com.master.core.dao.Dao;
import com.master.pedidos.model.cardapio.CardapioModel;

public interface CardapioDao extends Dao {

	CardapioModel builModel(String estabelecimento);

}
