package br.com.fiap.pousada.Models;

import java.time.LocalDate;

public class Recibo {
	private String razãoSocial;
	private String cnpj;
	private String idRecibo;
	private LocalDate dataemissao;
	private double valorTotal;
	
	public Recibo(String razãoSocial, String cnpj, String idRecibo, LocalDate dataemissao, double valorTotal) {
		this.razãoSocial = razãoSocial;
		this.cnpj = cnpj;
		this.idRecibo = idRecibo;
		this.dataemissao = dataemissao;
		this.valorTotal = valorTotal;
	}
	
	

	public Recibo() {}


	public String getRazãoSocial() {
		return razãoSocial;
	}

	public void setRazãoSocial(String razãoSocial) {
		this.razãoSocial = razãoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getIdRecibo() {
		return idRecibo;
	}

	public void setIdRecibo(String idRecibo) {
		this.idRecibo = idRecibo;
	}

	public LocalDate getDataemissao() {
		return dataemissao;
	}

	public void setDataemissao(LocalDate dataemissao) {
		this.dataemissao = dataemissao;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}



	@Override
	public String toString() {
		return "Recibo: " + razãoSocial + " cnpj: " + cnpj + " numeroRecibo: " + idRecibo
				+ " dataemissao: /n" + dataemissao + " valorTotal: " + valorTotal;
	}

	
	
	
	
}
