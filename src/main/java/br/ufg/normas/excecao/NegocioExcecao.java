package br.ufg.normas.excecao;

import br.ufg.normas.excecao.BaseExcecao;
import br.ufg.normas.modelo.RespostaHttp;

import java.util.List;

public class NegocioExcecao extends BaseExcecao
{

    public NegocioExcecao(List<RespostaHttp> respostasHttp){
        super.setRespostasHttp(respostasHttp);
    }

}
