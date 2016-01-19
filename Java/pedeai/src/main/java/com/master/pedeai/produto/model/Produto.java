package com.master.pedeai.produto.model;

public class Produto implements Comparable<Produto> {

	private String oid;
	private String codigo;
	private String nome;
	private String descricao;
	private String imagem;
	private boolean ativo;
	private int tipoProduto;
	private int ordem;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(int tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	@Override
	public int compareTo(Produto o) {
		return this.ordem - o.ordem;
	}

}
