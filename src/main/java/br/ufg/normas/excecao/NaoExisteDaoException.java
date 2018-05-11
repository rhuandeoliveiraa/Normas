package br.ufg.normas.excecao;

import br.ufg.normas.modelo.RespostaHttp;

import java.util.List;

public class  NaoExisteDaoException extends BaseExcecao{

    public NaoExisteDaoException(List<RespostaHttp> respostasHttp){
        super.setRespostasHttp(respostasHttp);
    }

    public NaoExisteDaoException(){}

    public NaoExisteDaoException(String description){
        super(description);
    }


}
