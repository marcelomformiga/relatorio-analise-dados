
package br.com.relatorio_dados.dto;


import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;


@Component(value = "relatorioDTO")
public class RelatorioDTO {

    @Getter
    @Setter
    private Integer quantidadeClientes;
    
    @Getter
    @Setter
    private Integer quantidadeVendedores;
    
    @Getter
    @Setter
    private Long idMaiorVenda;
    
    @Getter
    @Setter
    private String vendedorComMenorVendas;
    

    public RelatorioDTO() {
        
        super();
        
        this.quantidadeClientes = 0;
        this.quantidadeVendedores = 0;
    }


    @Override
    public String toString() {
        
        String quantidadeClientesString = "1. Quantidade de Clientes: " + this.quantidadeClientes + "\n";
        String quantidadeVendedoresString = "2. Quantidade de Vendedores: " + this.quantidadeVendedores + "\n";
        String idMaiorVendaString = "3. ID da Venda de Valor mais alto: " + this.idMaiorVenda + "\n";
        String vendedorComMenorVendasString = "4. Nome do Vendedor que menos vendeu: " + this.vendedorComMenorVendas;
        
        return quantidadeClientesString + quantidadeVendedoresString + idMaiorVendaString + vendedorComMenorVendasString;
    }
    
}