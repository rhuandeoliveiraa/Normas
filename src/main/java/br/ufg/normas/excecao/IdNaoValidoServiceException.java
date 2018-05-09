package br.ufg.normas.excecao;

public class IdNaoValidoServiceException extends RuntimeException {
    public IdNaoValidoServiceException(String message) {
        super(message);
    }
}
