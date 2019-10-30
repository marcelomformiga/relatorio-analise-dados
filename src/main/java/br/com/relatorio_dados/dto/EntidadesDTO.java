
package br.com.relatorio_dados.dto;


import br.com.relatorio_dados.domain.entity.Cliente;
import br.com.relatorio_dados.domain.entity.Venda;
import br.com.relatorio_dados.domain.entity.Vendedor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import lombok.Builder;
import lombok.Data;


@Component(value = "entidadesDTO")
@Data
public class EntidadesDTO {

    private List<Cliente> clientes;
    private List<Vendedor> vendedores;
    private List<Venda> vendas;
    
    
    public EntidadesDTO() {
        
        this.clientes = new ArrayList<>();
        this.vendedores = new ArrayList<>();
        this.vendas = new ArrayList<>();
    }

    @Builder
    public EntidadesDTO(List<Cliente> clientes, List<Vendedor> vendedores, List<Venda> vendas) {
        
        this.clientes = clientes;
        this.vendedores = vendedores;
        this.vendas = vendas;
    }
    
}