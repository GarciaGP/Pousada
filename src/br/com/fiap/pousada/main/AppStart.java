package br.com.fiap.pousada.main;
/*
 * Manutenção de fonte
 * Autor: Gabriel Garcia Pereira
 * Data: 30 / 08 / 2020
 * Manutenção: Implementação Inicial
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.fiap.pousada.BLL.BoQuarto;
import br.com.fiap.pousada.Menu.Menu;
import br.com.fiap.pousada.Models.Quarto;
import br.com.fiap.pousada.Models.Enums.Categoria;

public class AppStart {

	public static void main(String[] args) throws Exception {

		// Verifica se há registros na base, caso não tenha, cria os quartos

		List<Quarto> lstQuartos = new ArrayList<>();

		lstQuartos = new BoQuarto().consultarQuartos();

		if (lstQuartos == null || lstQuartos.isEmpty())
			gerarQuartos();

		try (Scanner scan = new Scanner(System.in)) {
			Menu menu = new Menu();
			int opcao = 0;

			do {
				menu.iniciaMenu();
				System.out.print("> ");
				opcao = scan.nextInt();
				scan.nextLine();

				switch (opcao) {
				case 1:
					menu.incluirReserva(scan);
					break;
				case 2:
					menu.consultarQuartos();
					break;
				case 3:
					menu.consultarReservas();
					break;
				}

				System.out.println("\n\n");
			} while (opcao != 0);

			System.out.println("Sistema finalizado com sucesso.");
		}

	}

	private static void gerarQuartos() {

		// TODO: Implementar mecanismo que gera quartos seguindo as regras do escopo

		List<Quarto> lstQuarto = new ArrayList<>();

		int contador = 0;
		int numero = 0;

		while (contador < 15) {
			Quarto quarto = new Quarto();

			numero = numero + 2;

			quarto.setNumero(numero);
			quarto.setCategoria(numero <= 20 ? Categoria.APARTAMENTO : Categoria.VIP);
			quarto.setMaxPessoas(numero < 20 ? definirMaximoPessoas(1, 5) : definirMaximoPessoas(1, 10));
			quarto.setValorDiaria(numero < 20 ? gerarValorDiaria(50.0, 150.0) : gerarValorDiaria(200.0, 300.0));
			
			contador++;
			lstQuarto.add(quarto);
		}

		new BoQuarto().incluirQuartos(lstQuarto);

	}
	
	private static double gerarValorDiaria(double min, double max){
	    double x = (Math.random()*((max-min)+1))+min;
	    return x;
	}
	
	private static int definirMaximoPessoas(int min, int max){
		int x = (int) ((Math.random()*((max-min)+1))+min);
	    return x;
	}

}
