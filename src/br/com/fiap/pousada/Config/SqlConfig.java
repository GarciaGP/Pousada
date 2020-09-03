/*
 * Manuten��o de fonte
 * Autor: Gabriel Garcia Pereira
 * Data: 30 / 08 / 2020
 * Manuten��o: Implementa��o Inicial
 */

package br.com.fiap.pousada.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Classe de configura��o do SQL
// Alterar somente o endere�o e dados de acesso do banco
public class SqlConfig {
	
	private Connection conn;
	
	public SqlConfig() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Especificar usu�rio e senha do banco nos �ltimos par�metros
			this.conn = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "", "");

		} catch (ClassNotFoundException e) {
			System.err.printf("N�o foi poss�vel localizar o driver: %s", e.getMessage());
		} catch (SQLException e) {
			System.err.println("Ocorreu um erro na conex�o com o banco de dados");
			System.err.println(e.getMessage());
		}
	}
	
	public Connection getConn() {
		return conn;
	}

}
