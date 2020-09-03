/*
 * Manuten��o de fonte
 * Autor: Gabriel Garcia Pereira
 * Data: 30 / 08 / 2020
 * Manuten��o: Implementa��o Inicial
 * 
 * Manuten��o de fonte
 * Autor: Helou�se / Matheus
 * Data: 01 / 09 / 2020
 * Manuten��o: Valida��es de datas adicionadas
 * 
 * Manuten��o de fonte
 * Autor: Gabriel Garcia Pereira / Matheus / Helou�se
 * Data: 02 / 09 / 2020
 * Manuten��o: Implementa��o Inicial
 */

package br.com.fiap.pousada.Menu;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import br.com.fiap.pousada.BLL.BoQuarto;
import br.com.fiap.pousada.BLL.BoRecibo;
import br.com.fiap.pousada.BLL.BoReserva;
import br.com.fiap.pousada.Models.Quarto;
import br.com.fiap.pousada.Models.Recibo;
import br.com.fiap.pousada.Models.Reserva;

public class Menu {

	public void iniciaMenu() {


		System.out.println("| POUSADA AMBERS	*	*	*");
		System.out.println("| Digite a op��o desejada:");
		System.out.println("| 1 - NOVA RESERVA");
		System.out.println("| 2 - CONSULTAR QUARTOS");
		System.out.println("| 3 - CONSULTAR RESERVAS");
		System.out.println("| 0 - Sair do sistema");
	}

	public void consultarReservas() {
		System.out.println("|----- Consulta de reservas -----|");
		List<Reserva> reservas = new BoReserva().consultarReserva();
		System.out.println("Listando Reservas...");
		reservas.forEach(System.out::println);
		System.out.println("|------ Fim do Consulta ------|");
	}

	public void consultarQuartos() {
		System.out.println("|----- Consulta de quartos -----|");
		List<Quarto> quartos = new BoQuarto().consultarQuartos();
		System.out.println("Listando os quartos...");
		quartos.forEach(System.out::println);
		System.out.println("|------ Fim do Consulta ------|");
	}

	public void incluirReserva(Scanner scan) throws ParseException {

		try {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

			System.out.println("|----- Registro de nova reserva -----|");

			System.out.print("Quarto reservado: ");
			int numeroQuarto = scan.nextInt();
			scan.nextLine();

			System.out.print("Data de entrada: ");
			String dataEntrada = scan.nextLine();
			LocalDate dtEntradaAux = LocalDate.parse(dataEntrada, formatter);

			System.out.print("Data de sa�da: ");
			String dataSaida = scan.nextLine();
			LocalDate dtSaidaAux = LocalDate.parse(dataSaida, formatter);

			System.out.print("Quantidade de pessoas: ");
			int qtdPessoas = scan.nextInt();

			Quarto quarto = new Quarto();

			quarto = new BoQuarto().obterQuarto(numeroQuarto);
			
			quarto.verificaDisponibilidadeQuarto(numeroQuarto);
		
			Reserva reserva = new Reserva(quarto, dtEntradaAux, dtSaidaAux, qtdPessoas);
			
			reserva.validarModel(reserva);

			new BoReserva().incluirReserva(reserva);
			
			System.out.println("|------ Reserva realizada com sucesso! ------|");
			
			new BoRecibo();
			Recibo recibo = BoRecibo.geraRecibo(reserva);
			System.out.println(recibo);

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println(e.getMessage());;
			restartProgram(scan);
		}

	}

	public void restartProgram(Scanner scan) throws ParseException {
		Menu menu = new Menu();
		iniciaMenu();
		System.out.print("> ");
		scan.nextLine();
		menu.incluirReserva(scan);
	}

}
