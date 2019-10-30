
package br.com.relatorio_dados.exceptions;


/**
 *
 * @author formiga
 */
public class ServiceException extends RuntimeException {
    
    public ServiceException() {
        super("Service Exception!");
    }
    
    public ServiceException(String mensagem, Exception exception) {
        super(mensagem, exception);
    }
    
}