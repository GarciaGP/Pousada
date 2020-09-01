/*
 * Manutenção de fonte
 * Autor: Gabriel Garcia Pereira
 * Data: 30 / 08 / 2020
 * Manutenção: Implementação Inicial
 */

package br.com.fiap.pousada.Menu;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import br.com.fiap.pousada.BLL.BoQuarto;
import br.com.fiap.pousada.BLL.BoReserva;
import br.com.fiap.pousada.Models.Quarto;
import br.com.fiap.pousada.Models.Reserva;
import static br.com.fiap.pousada.validator.InputValidator.isDataEntradaValida;
import static br.com.fiap.pousada.validator.InputValidator.isDataSaidaValida;

public class Menu {
	

	public static void iniciaMenu() {
			
		System.out.println("|-----------------------------|");
		System.out.println("|    POUSADA		      	  |");
		System.out.println("|                             |");
		System.out.println("| Digite a opção desejada:    |");
		System.out.println("| 1 - NOVA RESERVA  		  |");
		System.out.println("| 2 - CONSULTAR QUARTOS       |");
		System.out.println("| 3 - CONSULTAR RESERVAS      |");
		System.out.println("| 0 - Sair do sistema         |");
		System.out.println("|-----------------------------|");
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
			LocalDate dtEntradaAux = null;
			
			try {
				dtEntradaAux = LocalDate.parse(dataEntrada, formatter);
			}catch (Exception e) {
				System.out.println("Favor informar uma data válida no formato dd-MM-yyyy ");
				restartProgram(scan);
			}
			
			if(!isDataEntradaValida(dtEntradaAux)) {
				System.out.println("Data de entrada deve ser a mesma ou posterior a data atual, favor tente novamente");
				restartProgram(scan);
			}
			
		
			
			//TODO verificar se a data de entrada é anterior a data atual
			
			System.out.print("Data de saída: ");
			String dataSaida = scan.nextLine();

			LocalDate dtSaidaAux = null;
			
			try {
				dtSaidaAux = LocalDate.parse(dataSaida, formatter);
			}catch (Exception e) {
				System.out.println("Favor informar uma data válida no formato dd-MM-yyyy ");
				restartProgram(scan);
			}
			
			if(!isDataSaidaValida(dtSaidaAux, dtEntradaAux)) {
				System.out.println("Data de saída deve ser posterior a data atual em no mínimo 2 dias, favor tente novamente");
				restartProgram(scan);
			}	

			System.out.print("Quantidade de pessoas: ");
			int qtdPessoas = scan.nextInt();

			Quarto quarto = new Quarto();
			quarto = new BoQuarto().obterQuarto(numeroQuarto);

			Reserva reserva = new Reserva(
					quarto, 
					dtEntradaAux,
					dtSaidaAux, 
					qtdPessoas);

			new BoReserva().incluirReserva(reserva);
			System.out.println("|------ Fim do Cadastro ------|");
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

	public static void restartProgram(Scanner scan) throws ParseException {
		Menu menu = new Menu();
		iniciaMenu();
		System.out.print("> ");
		scan.nextLine();
		menu.incluirReserva(scan);
	}
	

}


