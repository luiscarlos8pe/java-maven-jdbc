package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SinngleConnection {
	private static String url ="jdbc:postgresql://localhost:5432/posjava";
	private static String user ="postgres";
	private static String password ="postgres";
	private static Connection connection = null;

	
	static {
		conectar(); 
	}
	public SinngleConnection() {
		conectar();
	}

	private static void conectar() {
		try {
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("conectou com sucesso");
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConnection() {
		return connection;
	}
}
