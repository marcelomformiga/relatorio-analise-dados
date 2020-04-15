
package br.com.relatorio_dados.domain.entity;


import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author formiga
 */
public abstract class Entidade {
    
    @Getter
    @Setter
    private Long id;
    
    @Getter
    @Setter
    private String codigo;

    
    public Entidade() {
        super();
    }

    public Entidade(Long id, String codigo) {
        this.id = id;
        this.codigo = codigo;
    }
    
}