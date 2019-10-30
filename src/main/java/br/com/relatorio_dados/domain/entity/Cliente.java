
package br.com.relatorio_dados.domain.entity;


import br.com.relatorio_dados.domain.enumeration.TipoInformacoesEnum;
import lombok.Builder;
import lombok.Data;


/**
 *
 * @author formiga
 */
@Data
public class Cliente extends Pessoa {
    
    private String cnpj;
    private String ramoAtividade;
    
    
    @Builder
    public Cliente(Long id, String cnpj, String nome, String ramoAtividade) {
        
        super(id, TipoInformacoesEnum.CLIENTE.getCODIGO(), nome);
        
        this.cnpj = cnpj;
        this.ramoAtividade = ramoAtividade;
    }
    
}