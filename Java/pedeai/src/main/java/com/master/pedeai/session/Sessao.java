package com.master.pedeai.session;

import java.io.Serializable;

import com.master.pedeai.estabelecimento.model.Estabelecimento;

public class Sessao implements Serializable {

	private Estabelecimento estabelecimento;

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

}
