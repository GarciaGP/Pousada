/*
 * Manuten��o de fonte
 * Autor: Gabriel Garcia Pereira
 * Data: Pousada - DDD
 * Manuten��o: Implementa��o Inicial
 */

package br.com.fiap.pousada.Models;

import java.util.List;

public class Pousada {
	private List<Reserva> reserva;

	// GETTERS SETTERS
	public List<Reserva> getReserva() {
		return reserva;
	}

	public void setReserva(List<Reserva> reserva) {
		this.reserva = reserva;
	}

}