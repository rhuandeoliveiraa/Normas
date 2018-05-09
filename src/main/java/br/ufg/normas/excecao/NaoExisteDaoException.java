package br.ufg.normas.excecao;

public class NaoExisteDaoException extends  RuntimeException{

    public NaoExisteDaoException(String message) {

        super(message);
    }

}
