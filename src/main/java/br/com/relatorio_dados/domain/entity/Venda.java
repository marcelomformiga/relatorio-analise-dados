
package br.com.relatorio_dados.domain.entity;


import br.com.relatorio_dados.domain.enumeration.TipoInformacoesEnum;
import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author formiga
 */
public class Venda extends Entidade {
    
    @Getter
    @Setter
    private List<Item> itens;
    
    @Getter
    @Setter
    private Vendedor vendedor;
    
    @Getter
    @Setter
    private BigDecimal totalDaVenda;

    
    @Builder
    public Venda(Long id, List<Item> itens, Vendedor vendedor, BigDecimal totalDaVenda) {
        
        super(id, TipoInformacoesEnum.VENDA.getCODIGO());
        
        this.itens = itens;
        this.vendedor = vendedor;
        this.totalDaVenda = totalDaVenda;
    }
    
}