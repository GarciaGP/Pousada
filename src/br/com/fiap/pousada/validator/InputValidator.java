package br.com.fiap.pousada.validator;

import java.time.LocalDate;


public class InputValidator {

	public static Boolean isDataEntradaValida(LocalDate dataEntrada) {

		if (dataEntrada.isBefore(LocalDate.now())) {
			return false;
		} else {
			return true;
		}

	}

	public static Boolean isDataSaidaValida(LocalDate dataSaida, LocalDate dataEntrada) {

		if (dataSaida.isBefore(dataEntrada.plusDays(2))) {
			return false;
		} else {
			return true;
		}
	}
}
