package com.master.pedeai.resource;

import com.master.core.resource.ResourceImpl;
import com.master.pedeai.estabelecimento.model.Estabelecimento;
import com.master.pedeai.session.Sessao;

public class ResourceWeb extends ResourceImpl {

	private Sessao sessao;


	public ResourceWeb(Sessao sessao) {
		this.sessao = sessao;
	}

	public Estabelecimento getEstabelecimento() {
		return this.sessao.getEstabelecimento();
	}


}
