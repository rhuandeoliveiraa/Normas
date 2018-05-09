package br.ufg.normas.excecao;

public class NegocioException extends RuntimeException {

    public NegocioException(String message){
        super(message);
    }
}
