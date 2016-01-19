package com.master.pedeai.cardapio.model;

import java.util.Set;

import com.master.pedeai.bloco.model.BlocoModel;

public class CardapioModel {

	private String oid;

	private boolean ativo;

	private String titulo;

	private String descricao;

	private Set<BlocoModel> blocos;

	public String getOid() {
		return this.oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public boolean isAtivo() {
		return this.ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Set<BlocoModel> getBlocos() {
		return this.blocos;
	}

	public void setBlocos(Set<BlocoModel> blocos) {
		this.blocos = blocos;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
