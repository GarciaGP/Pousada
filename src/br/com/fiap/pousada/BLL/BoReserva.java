/*
 * Manutenção de fonte
 * Autor: Gabriel Garcia Pereira
 * Data: 30 / 08 / 2020
 * Manutenção: Implementação Inicial
 */

package br.com.fiap.pousada.BLL;

import java.util.List;

import br.com.fiap.pousada.DAO.DispatcherReserva;
import br.com.fiap.pousada.Models.Reserva;

public class BoReserva {
	
	private DispatcherReserva dsp;
	
	public BoReserva() {
		this.dsp = new DispatcherReserva();
	}

	public List<Reserva> consultarReserva() {
		return dsp.consultarReservas();
	}

	public void incluirReserva(Reserva reserva) {

		dsp.incluirReserva(reserva);
	}
	
	
}
