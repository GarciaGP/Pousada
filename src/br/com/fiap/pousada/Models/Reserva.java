/*
 * Manutenção de fonte
 * Autor: Gabriel Garcia Pereira
 * Data: Pousada - DDD
 * Manutenção: Implementação Inicial
 */

package br.com.fiap.pousada.Models;

import java.time.LocalDate;

public class Reserva {
	private Quarto quarto;
	private LocalDate dataEntrada;
	private LocalDate dataSaida;
	private int quantidadePessoas;

	// CONSTRUTORES
	public Reserva(Quarto quarto, LocalDate dataEntrada, LocalDate dataSaida, int quantidadePessoas) {
		super();
		this.quarto = quarto;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.quantidadePessoas = quantidadePessoas;
	}

	// GETTERS SETTERS
	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}

	public int getQuantidadePessoas() {
		return quantidadePessoas;
	}

	public void setQuantidadePessoas(int quantidadePessoas) {
		this.quantidadePessoas = quantidadePessoas;
	}

}
