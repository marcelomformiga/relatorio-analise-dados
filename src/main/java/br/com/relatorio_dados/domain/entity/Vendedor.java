
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
public class Vendedor extends Pessoa {
    
    private String cpf;
    private BigDecimal salario;

    
    @Builder
    public Vendedor(Long id, String nome, String cpf, BigDecimal salario) {
        
        super(id, TipoInformacoesEnum.VENDEDOR.getCODIGO(), nome);
        
        this.cpf = cpf;
        this.salario = salario;
    }
    
}