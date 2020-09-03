/*
 * Manutenção de fonte
 * Autor: Gabriel Garcia Pereira
 * Data: 30 / 08 / 2020
 * Manutenção: Implementação Inicial
 */

package br.com.fiap.pousada.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Classe de configuração do SQL
// Alterar somente o endereço e dados de acesso do banco
public class SqlConfig {
	
	private Connection conn;
	
	public SqlConfig() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Especificar usuário e senha do banco nos últimos parâmetros
			this.conn = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "", "");

		} catch (ClassNotFoundException e) {
			System.err.printf("Não foi possível localizar o driver: %s", e.getMessage());
		} catch (SQLException e) {
			System.err.println("Ocorreu um erro na conexão com o banco de dados");
			System.err.println(e.getMessage());
		}
	}
	
	public Connection getConn() {
		return conn;
	}

}
