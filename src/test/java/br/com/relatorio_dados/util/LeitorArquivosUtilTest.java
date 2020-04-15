
package br.com.relatorio_dados.util;


import br.com.relatorio_dados.exceptions.ArquivoException;
import java.io.File;
import java.io.FileFilter;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;


/**
 *
 * @author formiga
 */
@RunWith(MockitoJUnitRunner.class)
public class LeitorArquivosUtilTest {
    
    @InjectMocks
    private LeitorArquivosUtil leitorArquivosUtil;
   
    private static final String EXTENSAO = ".dat";
    
    private final FileFilter FILTRO;
    
    public LeitorArquivosUtilTest() {
        
        FILTRO = new FileFilter() {
        
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(EXTENSAO);
            }
        };
    }

    @Before
    public void setUp() { }
    
    @After
    public void tearDown() {
        System.out.println("Fim do Teste");
    }
    
    @Test
    public void testRecuperarArquivosDeDiretorio_Sucesso() {
        
        String diretorio_in = "C:\\dados\\in\\";
        
        File[] resposta = this.leitorArquivosUtil.recuperarArquivosDATDeDiretorio(diretorio_in, FILTRO);
        
        Assert.assertNotNull("[1]Resposta não pode ser nula!", resposta);
        Assert.assertEquals("[2]Lista deve ter tamanho 2!", 2, resposta.length);
    }
    
    @Test
    public void testRecuperarArquivosDeDiretorio_DiretorioInvalido() {
        
        String diretorio = "C:\\input1.dat";
        
        File[] resposta = this.leitorArquivosUtil.recuperarArquivosDATDeDiretorio(diretorio, FILTRO);
        
        Assert.assertNull("[1]Resposta deve ser nula!", resposta);
    }
    
    @Test
    public void testRecuperarArquivosDeDiretorio_DiretorioInexistente() {
        
        String diretorio = "C:\\dat";
        
        File[] resposta = this.leitorArquivosUtil.recuperarArquivosDATDeDiretorio(diretorio, FILTRO);
        
        Assert.assertNull("[1]Resposta deve ser nula!", resposta);
    }
    
    @Test
    public void testLerArquivo_Sucesso() {
        
        File file = new File("C:\\dados\\in\\input1.dat");
        
        List<String> resposta = this.leitorArquivosUtil.lerArquivo(file);
        
        Assert.assertNotNull("[1]Resposta não pode ser nula!", resposta);
        Assert.assertEquals("[2]Lista deve ter tamanho 3!", 3, resposta.size());
    }
    
    @Test(expected = ArquivoException.class)
    public void testLerArquivo_ArquivoNaoEncontrado() {
        
        File file = new File("C:\\dados\\in\\input.dat");
        
        List<String> resposta = this.leitorArquivosUtil.lerArquivo(file);
        
        Assert.fail(resposta.toString());
    }
    
}