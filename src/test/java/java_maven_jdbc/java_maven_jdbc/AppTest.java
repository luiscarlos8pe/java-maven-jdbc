package java_maven_jdbc.java_maven_jdbc;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import conexao.SinngleConnection;
import dao.UserPosDao;
import model.BeanUserFone;
import model.Telefone;
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
    	pessoa.setNome("Henrique");
    	pessoa.setEmail("Henrique@gamil.com.br");
    	
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
    
    @Test
    public void initDeletar() {
    	
    	try {
    		UserPosDao UserPosDao = new UserPosDao();
        	UserPosDao.deletar(4L);
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
		
    }
    
    @Test
    public void initTelefone()
    {
    	Telefone telefone = new Telefone();
    	telefone.setNumero("(81) 00000-0000");
    	telefone.setTipo("casa");
    	telefone.setUsuario(5L);
    	
    	
    	UserPosDao daoPessoa = new UserPosDao();
    	daoPessoa.salvarTelefone(telefone);
    }
    
    @Test
    public void initBuscarFoneUser() throws SQLException {
    	Telefone telefone = new Telefone();
    	UserPosDao userPosDao = new UserPosDao();
    	
    	List<BeanUserFone> listaFone = userPosDao.listaUserFone(3L);
    	
    	for (BeanUserFone beanUserFone : listaFone) {
			System.out.println(beanUserFone);
			System.out.println("---------------");
		}
    }
    
    @Test
    public void initDeleteUserFone() {
    	
    	UserPosDao dao = new UserPosDao();
    	dao.deleteFonesPorUser(5L);
    	
    }
}
