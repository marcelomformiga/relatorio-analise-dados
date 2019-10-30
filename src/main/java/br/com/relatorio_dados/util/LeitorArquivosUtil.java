
package br.com.relatorio_dados.util;


import br.com.relatorio_dados.dto.RelatorioDTO;
import br.com.relatorio_dados.exceptions.ArquivoException;
import br.com.relatorio_dados.exceptions.ExceptionMensagens;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;


/**
 *
 * @author formiga
 */
@Component
public class LeitorArquivosUtil {
            
    public Boolean criarDiretorio(String nome) {
        
        Boolean arquivoCriado = Boolean.TRUE;
        
        File file = new File(nome);
        
        if (file.exists()) {
            arquivoCriado = Boolean.FALSE;
        } else {
            file.mkdir();
        }
        
        return arquivoCriado;
    }
    
    public File[] recuperarArquivosDATDeDiretorio(String diretorio, FileFilter filtro) {
        
        File[] files = null;
        
        File file = new File(diretorio);
        
        if ((file.exists()) && (file.isDirectory())) {
            
            files = file.listFiles(filtro);
        }
        
        return files;
    }
    
    public List<String> lerArquivo(File file) {
        
        List<String> linhas = new ArrayList<>();
        
        try {
                
            FileInputStream fileInputStream = new FileInputStream(file);

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String linha = bufferedReader.readLine();

            while (Objects.nonNull(linha)) {

                linhas.add(linha);
                
                linha = bufferedReader.readLine();
            }
            
        } catch (FileNotFoundException exception) {
            Logger.getLogger(LeitorArquivosUtil.class.getName()).log(Level.SEVERE, ExceptionMensagens.ARQUIVO_NAO_ENCONTRADO, exception);
            throw new ArquivoException(ExceptionMensagens.ARQUIVO_NAO_ENCONTRADO, exception);
        } catch (IOException exception) {
            Logger.getLogger(LeitorArquivosUtil.class.getName()).log(Level.SEVERE, ExceptionMensagens.FALHA_LEITURA_ARQUIVO, exception);
            throw new ArquivoException(ExceptionMensagens.FALHA_LEITURA_ARQUIVO, exception);
        }
        
        return linhas;
    }
    
    public void gravarRelatorioDeSaida(String nome, RelatorioDTO relatorioDTO) {
        
        try {
                
                File file = new File(nome);
                
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
                
                dataOutputStream.writeUTF(relatorioDTO.toString());
                
                fileOutputStream.close();
                
            } catch (FileNotFoundException exception) {
                Logger.getLogger(LeitorArquivosUtil.class.getName()).log(Level.SEVERE, ExceptionMensagens.ARQUIVO_NAO_ENCONTRADO, exception);
                throw new ArquivoException(ExceptionMensagens.ARQUIVO_NAO_ENCONTRADO, exception);
            } catch (IOException exception) {
                Logger.getLogger(LeitorArquivosUtil.class.getName()).log(Level.SEVERE, ExceptionMensagens.FALHA_LEITURA_ARQUIVO, exception);
                throw new ArquivoException(ExceptionMensagens.FALHA_LEITURA_ARQUIVO, exception);
            }
    }

}