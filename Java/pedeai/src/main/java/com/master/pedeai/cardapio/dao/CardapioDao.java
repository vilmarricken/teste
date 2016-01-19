package com.master.pedeai.cardapio.dao;

import com.master.core.dao.Dao;
import com.master.core.exception.MasterException;
import com.master.pedeai.cardapio.model.CardapioModel;

public interface CardapioDao extends Dao {

	CardapioModel builModel(String estabelecimento) throws MasterException;

}
