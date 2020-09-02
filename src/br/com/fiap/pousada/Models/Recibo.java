package br.com.fiap.pousada.Models;

import java.time.LocalDate;

public class Recibo {
	private String raz�oSocial;
	private String cnpj;
	private String idRecibo;
	private LocalDate dataemissao;
	private double valorTotal;
	
	public Recibo(String raz�oSocial, String cnpj, String idRecibo, LocalDate dataemissao, double valorTotal) {
		this.raz�oSocial = raz�oSocial;
		this.cnpj = cnpj;
		this.idRecibo = idRecibo;
		this.dataemissao = dataemissao;
		this.valorTotal = valorTotal;
	}
	
	

	public Recibo() {}


	public String getRaz�oSocial() {
		return raz�oSocial;
	}

	public void setRaz�oSocial(String raz�oSocial) {
		this.raz�oSocial = raz�oSocial;
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
		return "Recibo: " + raz�oSocial + " cnpj: " + cnpj + " numeroRecibo: " + idRecibo
				+ " dataemissao: /n" + dataemissao + " valorTotal: " + valorTotal;
	}

	
	
	
	
}
