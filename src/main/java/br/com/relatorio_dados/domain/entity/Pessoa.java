
package br.com.relatorio_dados.domain.entity;


import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author formiga
 */
public abstract class Pessoa extends Entidade {
    
    @Getter
    @Setter
    private String nome;

    
    public Pessoa() {
        super();
    }

    public Pessoa(Long id, String codigo) {
        super(id, codigo);
    }
    
    public Pessoa(Long id, String codigo, String nome) {
        
        super(id, codigo);

        this.nome = nome;
    }

}