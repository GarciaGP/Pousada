/*
 * Manutenção de fonte
 * Autor: Gabriel Garcia Pereira
 * Data: 26 / 08 / 2020
 * Manutenção: Implementação Inicial
 */

package br.com.fiap.pousada.Models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static br.com.fiap.pousada.validator.InputValidator.isDataEntradaValida;
import static br.com.fiap.pousada.validator.InputValidator.isDataSaidaValida;

public class Reserva {
	private Quarto quarto;
	private LocalDate dataEntrada;
	private LocalDate dataSaida;
	private int quantidadePessoas;

	public Reserva(Quarto quarto, LocalDate dataEntrada, LocalDate dataSaida, int quantidadePessoas) {
		super();
		this.quarto = quarto;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.quantidadePessoas = quantidadePessoas;
	}

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

	@Override
	public String toString() {
		return "Quarto: " + quarto.getNumero() + " || Data de entrada: " + dataEntrada + " || Data de saída: " + dataSaida
				+ " || Pessoas na reserva: " + quantidadePessoas;
	}

	public void validarModel(Reserva reserva) throws Exception {

			if (!isDataEntradaValida(reserva.dataEntrada))
				throw new Exception("Data de entrada inválida.");

			if (!isDataSaidaValida(reserva.dataSaida, reserva.dataEntrada))
				throw new Exception("Data de saída precisa ser no mínimo 2 dias após a data de entrada.");

			if (this.quantidadePessoas > this.quarto.getMaxPessoas())
				throw new Exception("O número de pessoas na reserva excede o máximo de pessoas para este quarto.");

	}
	
	public double calculaTotalHospedagem(LocalDate dataInicio, LocalDate dataFim, Quarto quarto) {
		long diferencaEmDias = ChronoUnit.DAYS.between(dataInicio, dataFim);

		return diferencaEmDias * quarto.getValorDiaria();
	}

}
