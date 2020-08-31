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
			
			for(Quarto quarto : quartos) {
						
				String sql = String.format("INSERT INTO T_QUARTO(NR_QUARTO, CATEGORIA, MAXPESSOAS, VLRDIARIA)"
						+ "VALUES(%d, %d, %d, %.0f)", quarto.getNumero(), quarto.getCategoria().ordinal(),
						quarto.getMaxPessoas(), quarto.getValorDiaria());
				
				Statement stmt = this.conn.createStatement();
				stmt.executeUpdate(sql);
				
			}			
		
			this.desconecta(this.conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void desconecta(Connection conn) throws SQLException {
		if(!conn.isClosed()) conn.close();
	}

	public List<Quarto> consultarQuartos() {

		try {

			Categoria ct = null;

			List<Quarto> quartos = new ArrayList<>();

			Statement stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from T_QUARTO");

			while (rs.next()) {
				int numeroQuarto = rs.getInt("NR_QUARTO");
				int categoria = rs.getInt("CATEGORIA");
				int maxPessoas = rs.getInt("MAXPESSOAS");
				Double valorDiaria = rs.getDouble("VLRDIARIA");

				switch (categoria) {
				case 0:
					ct = Categoria.APARTAMENTO;
				case 1:
					ct = Categoria.VIP;
				}

				quartos.add(new Quarto(numeroQuarto, ct, maxPessoas, valorDiaria));
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

			Statement stmt = this.conn.createStatement();

			String sql = String.format("SELECT * from T_QUARTO WHERE NR_QUARTO LIKE '%d'", numeroQuarto);

			ResultSet rs = stmt.executeQuery(sql);
			
			Quarto obj = new Quarto();
			
			while(rs.next()){
				obj.setNumero(rs.getInt("NR_QUARTO"));
				obj.setCategoria(Categoria.values()[rs.getInt("CATEGORIA")]);
				obj.setMaxPessoas(rs.getInt("MAXPESSOAS"));
				obj.setValorDiaria(rs.getDouble("VLRDIARIA"));
			}

		
			this.fecharConexao(this.conn);
			return obj;
		} catch (Exception e) {
			System.err.println("Quarto não encontrado.");
			System.err.println(e.getMessage());
			return null;
		}
	}

	public void fecharConexao(Connection conn) throws SQLException {
		if (!conn.isClosed())
			conn.close();
	}
	
}
