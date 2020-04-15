
package br.com.relatorio_dados.exceptions;


/**
 *
 * @author formiga
 */
public class ArquivoException extends RuntimeException {
    
    public ArquivoException() {
        super("Arquivo Exception!");
    }
    
    public ArquivoException(String mensagem, Exception exception) {
        super(mensagem, exception);
    }
    
}