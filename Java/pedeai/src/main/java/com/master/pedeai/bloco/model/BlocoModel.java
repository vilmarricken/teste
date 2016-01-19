package com.master.pedeai.bloco.model;

import java.util.Set;

import com.master.pedeai.produto.model.Produto;

public class BlocoModel implements Comparable<BlocoModel> {

	private String oid;

	private String titulo;

	private String descricao;

	private String imagemTitulo;

	private String background;

	private int ordem;

	private BlocoModel superior;

	private Set<BlocoModel> fillhos;

	private Set<Produto> produto;

	public BlocoModel() {
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

	public BlocoModel getSuperior() {
		return superior;
	}

	public void setSuperior(BlocoModel superior) {
		this.superior = superior;
	}

	public Set<BlocoModel> getFillhos() {
		return fillhos;
	}

	public void setFillhos(Set<BlocoModel> fillhos) {
		this.fillhos = fillhos;
	}

	@Override
	public int compareTo(BlocoModel o) {
		return this.ordem - o.ordem;
	}
	
	public Set<Produto> getProduto() {
		return produto;
	}
	
	public void setProduto(Set<Produto> produto) {
		this.produto = produto;
	}
	
}
