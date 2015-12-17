package com.master.pedidos.model.cardapio;

import java.util.List;
import java.util.Set;

public class Bloco implements Comparable<Bloco> {

	private String oid;

	private String titulo;

	private String descricao;

	private String imagemTitulo;

	private String background;

	private int ordem;

	private Bloco superior;

	private Set<Bloco> fillhos;

	private Set<Produto> produto;

	public Bloco() {
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagemTitulo() {
		return imagemTitulo;
	}

	public void setImagemTitulo(String imagemTitulo) {
		this.imagemTitulo = imagemTitulo;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public Bloco getSuperior() {
		return superior;
	}

	public void setSuperior(Bloco superior) {
		this.superior = superior;
	}

	public Set<Bloco> getFillhos() {
		return fillhos;
	}

	public void setFillhos(Set<Bloco> fillhos) {
		this.fillhos = fillhos;
	}

	@Override
	public int compareTo(Bloco o) {
		return this.ordem - o.ordem;
	}
	
	public Set<Produto> getProduto() {
		return produto;
	}
	
	public void setProduto(Set<Produto> produto) {
		this.produto = produto;
	}
	
}
