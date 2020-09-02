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
		
		if(lstQuartos == null || lstQuartos.isEmpty()) 
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
				case 2:
					menu.consultarQuartos();
				case 3:
					menu.consultarReservas();
				}

				System.out.println("\n\n");
			} while (opcao != 0);

			System.out.println("Sistema finalizado com sucesso.");
		}

	}

	private static void gerarQuartos() {
		
		// TODO: Implementar mecanismo que gera quartos seguindo as regras do escopo
		
		List<Quarto> lstQuarto = new ArrayList<>();
		
		Quarto quarto = new Quarto();
		
		quarto.setNumero(3);
		quarto.setCategoria(Categoria.APARTAMENTO);
		quarto.setMaxPessoas(10);
		quarto.setValorDiaria(100.00);
		
		lstQuarto.add(quarto);
		
		new BoQuarto().incluirQuartos(lstQuarto);
		
	}
	
	
}
