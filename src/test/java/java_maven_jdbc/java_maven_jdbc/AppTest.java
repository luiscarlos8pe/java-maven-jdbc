package java_maven_jdbc.java_maven_jdbc;

import static org.junit.Assert.assertTrue;

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
}
