
package br.com.relatorio_dados.domain.entity;


import br.com.relatorio_dados.domain.enumeration.TipoInformacoesEnum;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;


/**
 *
 * @author formiga
 */
@Data
public class Item extends Entidade {
    
    private Integer quantidade;
    private BigDecimal preco;

    
    public Item() {
        super();
    }
    
    @Builder
    public Item(Long id, Integer quantidade, BigDecimal preco) {
        
        super(id, TipoInformacoesEnum.ITEM.getCODIGO());
        
        this.quantidade = quantidade;
        this.preco = preco;
    }
    
}