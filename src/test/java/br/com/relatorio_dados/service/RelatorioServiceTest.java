
package br.com.relatorio_dados.service;


import br.com.relatorio_dados.service.impl.RelatorioServiceImpl;
import br.com.relatorio_dados.dto.RelatorioDTO;
import br.com.relatorio_dados.util.LeitorArquivosUtil;
import java.io.File;
import java.io.FileFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


/**
 *
 * @author formiga
 */
@RunWith(MockitoJUnitRunner.class)
public class RelatorioServiceTest {
    
    @InjectMocks
    private RelatorioServiceImpl relatorioService;
    
    @Mock
    private LeitorArquivosUtil leitorArquivosUtilMock;
    
    private static final String EXTENSAO = ".dat";
    
    private final FileFilter FILTRO;
    
    public RelatorioServiceTest() {
        
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
//    @Ignore
    public void testMontarRelatorio_Sucesso() {
        
        Mockito.doCallRealMethod().when(this.leitorArquivosUtilMock).recuperarArquivosDATDeDiretorio(Mockito.anyString(), Mockito.any(FileFilter.class));
        Mockito.doCallRealMethod().when(this.leitorArquivosUtilMock).lerArquivo(Mockito.any());
        Mockito.doCallRealMethod().when(this.leitorArquivosUtilMock).criarDiretorio(Mockito.anyString());
        Mockito.doCallRealMethod().when(this.leitorArquivosUtilMock).gravarRelatorioDeSaida(Mockito.anyString(), Mockito.any(RelatorioDTO.class));
        
        this.relatorioService.montarRelatorio();
        
        Mockito.verify(this.leitorArquivosUtilMock, Mockito.times(1)).recuperarArquivosDATDeDiretorio(Mockito.anyString(), Mockito.any(FileFilter.class));
        Mockito.verify(this.leitorArquivosUtilMock, Mockito.times(1)).lerArquivo(Mockito.any());
        Mockito.verify(this.leitorArquivosUtilMock, Mockito.times(1)).criarDiretorio(Mockito.anyString());
        Mockito.verify(this.leitorArquivosUtilMock, Mockito.times(1)).gravarRelatorioDeSaida(Mockito.anyString(), Mockito.any(RelatorioDTO.class));
    }
    
}
