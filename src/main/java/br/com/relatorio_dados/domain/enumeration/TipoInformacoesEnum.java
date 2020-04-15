
package br.com.relatorio_dados.domain.enumeration;


/**
 *
 * @author formiga
 */
public enum TipoInformacoesEnum {
    
    VENDEDOR("001"),
    CLIENTE("002"),
    VENDA("003"),
    ITEM("004");
    
    
    private final String CODIGO;
    
    
    private TipoInformacoesEnum(String codigo) {
        this.CODIGO = codigo;
    }

    
    public String getCODIGO() {
        return this.CODIGO;
    }
    
}