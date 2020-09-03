/*
 * Manutenção de fonte
 * Autor: Gabriel Garcia Pereira
 * Data: 30 / 08 / 2020
 * Manutenção: Implementação Inicial
 */

package br.com.fiap.pousada.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.pousada.Config.SqlConfig;
import br.com.fiap.pousada.Models.Quarto;
import br.com.fiap.pousada.Models.Enums.Categoria;

// Classe de acesso ao banco - Quarto
public class DispatcherQuarto {

	private Connection conn;

	public DispatcherQuarto() {
		this.conn = new SqlConfig().getConn();
	}

	public void incluirQuartos(List<Quarto> quartos) {

		try {

			for (Quarto quarto : quartos) {

				String sql = String.format(
						"INSERT INTO T_QUARTO(NR_QUARTO, CATEGORIA, MAXPESSOAS, VLRDIARIA)"
								+ "VALUES(%d, %d, %d, %.00f)",
						quarto.getNumero(), quarto.getCategoria().ordinal(), quarto.getMaxPessoas(),
						quarto.getValorDiaria());

				Statement stmt = this.conn.createStatement();
				stmt.executeUpdate(sql);

			}

			this.desconecta(this.conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.desconecta(this.conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void desconecta(Connection conn) throws SQLException {
		if (!conn.isClosed())
			conn.close();
	}

	public List<Quarto> consultarQuartos() {

		try {

			Categoria ct = null;

			List<Quarto> quartos = new ArrayList<>();

			Statement stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from T_QUARTO");

			while (rs.next()) {
				int numeroQuarto = rs.getInt("NR_QUARTO");
				Categoria categoria = Categoria.values()[rs.getInt("CATEGORIA")];
				int maxPessoas = rs.getInt("MAXPESSOAS");
				Double valorDiaria = rs.getDouble("VLRDIARIA");

				quartos.add(new Quarto(numeroQuarto, categoria, maxPessoas, valorDiaria));
			}

			this.fecharConexao(this.conn);
			return quartos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public Quarto obterQuarto(int numeroQuarto) {

		try {

			Quarto quarto = null;
			PreparedStatement pstmt = null;
			String sql = String.format("SELECT * from T_QUARTO WHERE NR_QUARTO LIKE '%d'", numeroQuarto);

			pstmt = this.conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				quarto = new Quarto(rs.getInt("NR_QUARTO"), Categoria.values()[rs.getInt("CATEGORIA")],
						rs.getInt("MAXPESSOAS"), rs.getDouble("VLRDIARIA"));

			}

			this.fecharConexao(this.conn);
			return quarto;
		} catch (Exception e) {
			System.err.println("Quarto não encontrado.");
			System.err.println(e.getMessage());
			return null;
		}
	}

	public boolean verificaDisponibilidadeQuarto(int numeroQuarto) throws SQLException {

		try {

			PreparedStatement pstmt = null;
			String sql = String.format("SELECT * from T_RESERVA WHERE QUARTO_RESERVA LIKE '%d'", numeroQuarto);

			pstmt = this.conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			
			return rs.next() ? true : false;


		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		} finally {
			this.fecharConexao(this.conn);
		}
	}

	public void fecharConexao(Connection conn) throws SQLException {
		if (!conn.isClosed())
			conn.close();
	}

}
