package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao.SinngleConnection;
import model.Userposjava;

public class UserPosDao {

	
	private Connection connection;
	
	public UserPosDao(){
		connection = SinngleConnection.getConnection();
	}
	
	
	public void salvar(Userposjava  userposjava) {
		
		try {
			String sql = "insert into userposjava (id, nome, email) values(?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setLong(1, userposjava.getId());
			insert.setString(2, userposjava.getNome());
			insert.setString(3, userposjava.getEmail());
			insert.execute();
			connection.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
