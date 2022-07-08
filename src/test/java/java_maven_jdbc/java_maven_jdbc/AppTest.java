package java_maven_jdbc.java_maven_jdbc;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import conexao.SinngleConnection;
import dao.UserPosDao;
import model.Userposjava;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void initBanco()
    {
    	SinngleConnection.getConnection();
    }
    
    @Test
    public void initSalvarPessoa()
    {
    	Userposjava pessoa = new Userposjava();
    	pessoa.setId(3l);
    	pessoa.setNome("Otavio");
    	pessoa.setEmail("otavio@gamil.com.br");
    	
    	UserPosDao daoPessoa = new UserPosDao();
    	daoPessoa.salvar(pessoa);
    }
    
    @Test
    public void initListarTodos() {
		try {
			UserPosDao UserPosDao = new UserPosDao();
	    	List<Userposjava> usuario;
			usuario = UserPosDao.buscarTodos();
			for (Userposjava userposjava : usuario) {
				/*System.out.println(userposjava.getId());
				System.out.println(userposjava.getNome());
				System.out.println(userposjava.getEmail()+ "\n");*/
				System.out.println(userposjava);
				System.out.println("------------------------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    }
    
    @Test
    public void initListarPorId() {
		try {
			UserPosDao UserPosDao = new UserPosDao();
			Userposjava pessoa = new Userposjava();
			pessoa = UserPosDao.buscarPorId(2L);	
		    System.out.println(pessoa);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	
    }
    
    @Test
    public void initAtualizarNome() {
		try {
			UserPosDao UserPosDao = new UserPosDao();
			Userposjava pessoa = new Userposjava();
			pessoa = UserPosDao.buscarPorId(2L);
			
			
			System.out.println(pessoa);
			System.out.println("-------------");
			
			pessoa.setNome("Nome novo");
			UserPosDao.atualizar(pessoa);
		    System.out.println(pessoa);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	
    }
}
