/*
 * Manutenção de fonte
 * Autor: Gabriel Garcia Pereira
 * Data: 30 / 08 / 2020
 * Manutenção: Implementação Inicial
 */

package br.com.fiap.pousada.BLL;

import java.util.List;

import br.com.fiap.pousada.DAO.DispatcherQuarto;
import br.com.fiap.pousada.Models.Quarto;

public class BoQuarto {

	private DispatcherQuarto dsp;

	public BoQuarto() {
		this.dsp = new DispatcherQuarto();
	}

	public void incluirQuartos(List<Quarto> quartos) {
		dsp.incluirQuartos(quartos);
	}

	public List<Quarto> consultarQuartos() {
		List<Quarto> quartos = dsp.consultarQuartos();

		return quartos;
	}

	public Quarto obterQuarto(int numeroQuarto) throws Exception {
		
		Quarto quarto = dsp.obterQuarto(numeroQuarto);
		
		if (quarto == null)
			throw new Exception("O quarto não existe!");
		
		return quarto;
	}

	public boolean verificaDisponibilidadeQuarto(int numeroQuarto) throws Exception {
		
		boolean quartoExiste = dsp.verificaDisponibilidadeQuarto(numeroQuarto);
		
		if(quartoExiste)
			throw new Exception("Este quarto não está disponível.");
		
		return quartoExiste;
	}
}
