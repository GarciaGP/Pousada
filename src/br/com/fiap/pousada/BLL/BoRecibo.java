package br.com.fiap.pousada.BLL;

import java.time.LocalDate;
import java.util.UUID;

import br.com.fiap.pousada.Models.Recibo;
import br.com.fiap.pousada.Models.Reserva;

public class BoRecibo {
	private final static String CNPJ = "07.888.393/0001-60";
	private final static String RAZAO_SOCIAL = "POUSADA MAR E SOL S.A";
	
	public static Recibo geraRecibo(Reserva reserva) {
		double valorTotal = reserva.calculaTotalHospedagem(reserva.getDataEntrada(), reserva.getDataSaida(), reserva.getQuarto());
		Recibo recibo = new Recibo();
		recibo.setIdRecibo(UUID.randomUUID().toString());
		recibo.setDataemissao(LocalDate.now());
		recibo.setValorTotal(valorTotal);
		
		recibo.setCnpj(CNPJ);
		recibo.setRazãoSocial(RAZAO_SOCIAL);
		return recibo;
	}

}
