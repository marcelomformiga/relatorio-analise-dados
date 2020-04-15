
package br.com.relatorio_dados.service.impl;


import br.com.relatorio_dados.domain.entity.Cliente;
import br.com.relatorio_dados.domain.entity.Item;
import br.com.relatorio_dados.domain.entity.Venda;
import br.com.relatorio_dados.domain.entity.Vendedor;
import br.com.relatorio_dados.domain.enumeration.TipoInformacoesEnum;
import br.com.relatorio_dados.dto.EntidadesDTO;
import br.com.relatorio_dados.dto.RelatorioDTO;
import br.com.relatorio_dados.service.RelatorioService;
import br.com.relatorio_dados.util.LeitorArquivosUtil;
import java.io.File;
import java.io.FileFilter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.util.StringUtils;


/**
 *
 * @author formiga
 */
@Service
@Configurable
public class RelatorioServiceImpl implements RelatorioService {
    
    private static final String DIRETORIO_IN = "C:\\dados\\in\\";
    private static final String DIRETORIO_OUT = "C:\\dados\\out\\";
    
    private static final String EXTENSAO_DAT = ".dat";
    
    private static final FileFilter FILTRO_ARQUIVOS_DAT = new FileFilter() {
        
        @Override
        public boolean accept(File file) {
            return file.getName().endsWith(EXTENSAO_DAT);
        }
    };
    
    private static final String SEPARADOR_DE_LINHA = "ç";
    
    @Autowired
    @Getter
    private RelatorioDTO relatorioDTO;
    
    @Autowired
    @Getter
    private EntidadesDTO entidadesDTO;
        
    @Getter
    private LeitorArquivosUtil leitorArquivosUtil;
    
    
    public RelatorioServiceImpl() {
        super();
    }
    
    @Autowired
    public RelatorioServiceImpl(LeitorArquivosUtil leitorArquivosUtil) {
        this.leitorArquivosUtil = leitorArquivosUtil;
        this.relatorioDTO = new RelatorioDTO();
        this.entidadesDTO = new EntidadesDTO();
    }
    
    
    @Override
    public void montarRelatorio() {
        
        File[] files = this.leitorArquivosUtil.recuperarArquivosDATDeDiretorio(DIRETORIO_IN, FILTRO_ARQUIVOS_DAT);
        
        if ((Objects.nonNull(files)) && (files.length > 0)) {
            
            for (int i = 0; i < files.length; i ++) {
                
                List<String> linhas = this.leitorArquivosUtil.lerArquivo(files[i]);
                
                this.separarLinhasPorConteudo(linhas);
            }
            
            this.recuperarDadosFinalDoRelatorio();
            
            this.leitorArquivosUtil.criarDiretorio(DIRETORIO_OUT);
            
            String nomeArquivoSaida = DIRETORIO_OUT + "output.done" + EXTENSAO_DAT;
            
            this.leitorArquivosUtil.gravarRelatorioDeSaida(nomeArquivoSaida, this.relatorioDTO);
        }
    }
    
    private void separarLinhasPorConteudo(List<String> linhas) {
        
        linhas.stream().map((linha) ->
                StringUtils.isEmpty(linha) ? null : linha.split(RelatorioServiceImpl.SEPARADOR_DE_LINHA)).filter((conteudoDaLinha) ->
                        (Objects.nonNull(conteudoDaLinha))).forEachOrdered((conteudoDaLinha) -> {
                            
            String tipoInformacao = conteudoDaLinha[0];
            
            if (TipoInformacoesEnum.VENDEDOR.getCODIGO().equals(tipoInformacao)) {
                
                this.recuperarDadosDoVendedor(conteudoDaLinha);
                
            } else if (TipoInformacoesEnum.CLIENTE.getCODIGO().equals(tipoInformacao)) {
                
                this.recuperarDadosDoCliente(conteudoDaLinha);
                
            } else {
                
                this.recuperarDadosDaVenda(conteudoDaLinha);
            }
        });
    }
    
    private void recuperarDadosDoVendedor(String[] dadosDoVendedor) {
        
        Vendedor vendedor = Vendedor.builder()
                .cpf(dadosDoVendedor[1])
                .nome(dadosDoVendedor[2])
                .salario(BigDecimal.valueOf(Double.valueOf(dadosDoVendedor[3])))
                .build();
        
        this.entidadesDTO.getVendedores().add(vendedor);
    }
    
    private void recuperarDadosDoCliente(String[] dadosDoCliente) {
        
        Cliente cliente = Cliente.builder()
                .cnpj(dadosDoCliente[1])
                .nome(dadosDoCliente[2])
                .ramoAtividade(dadosDoCliente[3])
                .build();
        
        this.entidadesDTO.getClientes().add(cliente);
    }
            
    private void recuperarDadosDaVenda(String[] dadosDaVenda) {
        
        List<Item> itens = this.recuperarItensDaVenda(dadosDaVenda[2]);
        BigDecimal totalDaVenda = this.somarTotalDeVendaPorItem(itens);
        
        Vendedor vendedor = Vendedor.builder()
                .nome(dadosDaVenda[3])
                .build();
        
        Venda venda = Venda.builder()
                .id(Long.valueOf(dadosDaVenda[1]))
                .itens(itens)
                .vendedor(vendedor)
                .totalDaVenda(totalDaVenda)
                .build();
                
        this.entidadesDTO.getVendas().add(venda);
    }
    
    private List<Item> recuperarItensDaVenda(String itens) {
        
        List<Item> itensList = new ArrayList<>();
        
        String itensFormatados = itens.substring(1, itens.length() - 1);
        
        String[] itensExtraidos = itensFormatados.split(",");
        
        for (String itemExtraido : itensExtraidos) {
            String[] dadosDoItem = itemExtraido.split("-");
            
            Item item = Item.builder()
                .id(Long.valueOf(dadosDoItem[0]))
                .quantidade(Integer.valueOf(dadosDoItem[1]))
                .preco(new BigDecimal(dadosDoItem[2]))
                .build();
            
            itensList.add(item);
        }
        
        return itensList;
    }
    
    private BigDecimal somarTotalDeVendaPorItem(List<Item> itens) {
        
        BigDecimal totalDaVenda = BigDecimal.ZERO;
                
        itens.forEach((item) -> {
            totalDaVenda.add(item.getPreco());
        });
        
        return totalDaVenda;
    }
    
    private void recuperarDadosFinalDoRelatorio() {
        
        this.relatorioDTO.setQuantidadeClientes(this.entidadesDTO.getClientes().size());
        this.relatorioDTO.setQuantidadeVendedores(this.entidadesDTO.getVendedores().size());
        this.relatorioDTO.setIdMaiorVenda(this.recuperarMaiorVenda().getId());
        this.relatorioDTO.setVendedorComMenorVendas(this.recuperarVendedorComMenosVendas().getNome());

        System.out.println(this.relatorioDTO.toString());
    }
    
    private Venda recuperarMaiorVenda() {
        
        Venda maiorVenda = null;
        
        if ((Objects.nonNull(this.entidadesDTO.getVendas())) && (!this.entidadesDTO.getVendas().isEmpty())) {
            
            maiorVenda = this.entidadesDTO.getVendas().get(0);
            
            for (Venda vendaAux : this.entidadesDTO.getVendas()) {
                
                if (maiorVenda.getTotalDaVenda().compareTo(vendaAux.getTotalDaVenda()) == -1) {
                    
                    maiorVenda = vendaAux;
                }
            }
        }

        return maiorVenda;
    }
    
    private Vendedor recuperarVendedorComMenosVendas() {
        
        Vendedor vendedorComMenosVendas = null;
        
        if ((Objects.nonNull(this.entidadesDTO.getVendedores())) && (!this.entidadesDTO.getVendedores().isEmpty())) {
            
            vendedorComMenosVendas = this.entidadesDTO.getVendedores().get(0);
            
            int quantidadeDeVendas = 0;
            
            for (Vendedor vendedorAux : this.entidadesDTO.getVendedores()) {
                
                int contador = 0;
                
                if ((Objects.nonNull(this.entidadesDTO.getVendas())) && (!this.entidadesDTO.getVendas().isEmpty())) {
                    
                    for (Venda vendaAux : this.entidadesDTO.getVendas()) {
                
                        if (vendedorComMenosVendas.getNome().equals(vendaAux.getVendedor().getNome())) {

                            contador ++;
                        }
                    }
                    
                    if (contador > quantidadeDeVendas) {
                        
                        quantidadeDeVendas = contador;
                        
                        vendedorComMenosVendas = vendedorAux;
                    }
                }
            }
        }

        return vendedorComMenosVendas;
    }
    
}