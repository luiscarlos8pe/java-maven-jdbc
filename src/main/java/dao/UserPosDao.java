package dao;

import java.beans.Beans;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.SinngleConnection;
import model.Userposjava;
import model.BeanUserFone;
import model.Telefone;

public class UserPosDao {

	private Connection connection;

	public UserPosDao() {
		connection = SinngleConnection.getConnection();
	}

	public void salvar(Userposjava userposjava) {

		try {
			String sql = "insert into userposjava (nome, email) values(?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userposjava.getNome());
			insert.setString(2, userposjava.getEmail());
			insert.execute();
			connection.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void salvarTelefone(Telefone telefone) {

		try {
			String sql = "insert into telefoneuser (numero, tipo, usuariopessoa) values(?,?,?)";

			PreparedStatement insertTelefone = connection.prepareStatement(sql);
			insertTelefone.setString(1, telefone.getNumero());
			insertTelefone.setString(2, telefone.getTipo());
			insertTelefone.setLong(3, telefone.getUsuario());
			insertTelefone.execute();
			connection.commit();

			insertTelefone.setString(1, telefone.getNumero());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Userposjava> buscarTodos() throws SQLException {
		List<Userposjava> lista = new ArrayList<Userposjava>();

		String sql = "select * from userposjava";
		PreparedStatement listar = connection.prepareStatement(sql);
		ResultSet resultado = listar.executeQuery();

		while (resultado.next()) {
			Userposjava userposjava = new Userposjava();
			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));

			lista.add(userposjava);
		}

		return lista;
	}

	public Userposjava buscarPorId(Long id) throws SQLException {
		Userposjava retorno = new Userposjava();

		String sql = "select * from userposjava where id = " + id;
		PreparedStatement listar = connection.prepareStatement(sql);
		ResultSet resultado = listar.executeQuery();

		while (resultado.next()) {
			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));
		}

		return retorno;
	}

	public void atualizar(Userposjava userposjava) {

		try {

			String sql = "UPDATE userposjava SET nome = ? WHERE id = " + userposjava.getId();
			PreparedStatement atualizar = connection.prepareStatement(sql);
			atualizar.setString(1, userposjava.getNome());

			atualizar.execute();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public void deletar(Long id) {
		try {
			String sql = "delete from userposjava where id =" + id;
			PreparedStatement deletar = connection.prepareStatement(sql);
			deletar.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}
	
	public List<BeanUserFone> listaUserFone(Long idUser) throws SQLException {
		List<BeanUserFone> lista = new ArrayList<BeanUserFone>();

		String sql = "select nome, numero, email from userposjava as userp\r\n" + 
				"inner join telefoneuser as fone\r\n" + 
				"on userp.id = " + idUser;
		
		
		/*String sql = " select nome, numero, email from userposjava as userp " + 
				sql += " inner join telefoneuser as fone ";
				sql += " on userp.id = " + idUser;*/
		PreparedStatement listar = connection.prepareStatement(sql);
		ResultSet resultado = listar.executeQuery();

		while (resultado.next()) {
			BeanUserFone beanUserFone = new BeanUserFone();
			beanUserFone.setNome(resultado.getString("nome"));
			beanUserFone.setNumero(resultado.getString("numero"));
			beanUserFone.setEmail(resultado.getString("email"));

			lista.add(beanUserFone);
		}

		return lista;
	}
	
	public void deleteFonesPorUser(Long idUser) {
		
		String sqlFone = "delete from telefoneuser where id = " + idUser;
		String sqlUser = "delete from userposjava where id = " + idUser;
		try {
			PreparedStatement deleteFone = connection.prepareStatement(sqlFone);
			deleteFone.executeUpdate();
			connection.commit();
			
			PreparedStatement deleteUser = connection.prepareStatement(sqlUser);
			deleteUser.executeUpdate();
			connection.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
