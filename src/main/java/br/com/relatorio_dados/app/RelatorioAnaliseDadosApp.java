
package br.com.relatorio_dados.app;


import br.com.relatorio_dados.service.RelatorioService;
import br.com.relatorio_dados.service.impl.RelatorioServiceImpl;
import br.com.relatorio_dados.util.LeitorArquivosUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RelatorioAnaliseDadosApp {

    private static RelatorioService relatorioService;
    private static LeitorArquivosUtil leitorArquivosUtil;
    
    
    public static void main(String[] args) {
        SpringApplication.run(RelatorioAnaliseDadosApp.class, args);
        
        RelatorioAnaliseDadosApp.executarAplicacao();
    }
        
    public static void executarAplicacao() {
        
        RelatorioAnaliseDadosApp.leitorArquivosUtil = new LeitorArquivosUtil();
        
        RelatorioAnaliseDadosApp.relatorioService = new RelatorioServiceImpl(leitorArquivosUtil);
        RelatorioAnaliseDadosApp.relatorioService.montarRelatorio();
    }
    
}