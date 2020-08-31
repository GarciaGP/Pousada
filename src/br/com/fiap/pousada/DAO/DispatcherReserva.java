/*
 * Manutenção de fonte
 * Autor: Gabriel Garcia Pereira
 * Data: 30 / 08 / 2020
 * Manutenção: Implementação Inicial
 */

package br.com.fiap.pousada.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fiap.pousada.Config.SqlConfig;
import br.com.fiap.pousada.Models.Quarto;
import br.com.fiap.pousada.Models.Reserva;

// Classe de acesso ao banco - Reserva
public class DispatcherReserva {

	private Connection conn;

	public DispatcherReserva() {
		this.conn = new SqlConfig().getConn();
	}

	public void incluirReserva(Reserva reserva) {

		try {
			String entradaAux = reserva.getDataEntrada().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			String saidaAux = reserva.getDataSaida().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		
			
			String sql = String.format(
					"INSERT INTO T_RESERVA(ID_RESERVA, QUARTO_RESERVA, DTENTRADA, DTSAIDA, QTDPESSOAS)"
							+ "VALUES(SQ_RESERVA.nextval, %d, '%s','%s', %d)",
					reserva.getQuarto().getNumero(), 
					entradaAux, 
					saidaAux,
					reserva.getQuantidadePessoas());

			Statement stmt = this.conn.createStatement();
			stmt.executeUpdate(sql);
			
			System.out.print("Reserva feita com sucesso!");
			
			this.desconecta(this.conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	public void desconecta(Connection conn) throws SQLException {
		if (!conn.isClosed())
			conn.close();
	}

	public List<Reserva> consultarReservas() {

		try {

			List<Reserva> reservas = new ArrayList<>();

			Statement stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from T_RESERVA");

			while (rs.next()) {
				int numeroQuarto = rs.getInt("QUARTO_RESERVA");
				Date dtEntrada = rs.getDate("DTENTRADA");
				Date dtSaida = rs.getDate("DTSAIDA");
				int qtPessoas = rs.getInt("QTDPESSOAS");
				
				LocalDate entradaAux = new java.sql.Date(dtEntrada.getTime()).toLocalDate();
				LocalDate saidaAux = new java.sql.Date(dtSaida.getTime()).toLocalDate();
				
				Quarto quarto = new Quarto();
				quarto.setNumero(numeroQuarto);
				 reservas.add(new Reserva(quarto, entradaAux, saidaAux, qtPessoas));
			}

			this.fecharConexao(this.conn);
			return reservas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void fecharConexao(Connection conn) throws SQLException {
		if (!conn.isClosed())
			conn.close();
	}

}
