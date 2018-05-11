package br.ufg.normas.excecao;

import br.ufg.normas.modelo.RespostaHttp;

import java.util.ArrayList;
import java.util.List;

public class BaseExcecao extends RuntimeException {

    private List<RespostaHttp> respostasHttp = new ArrayList<>();

    public BaseExcecao() {
    }

    public BaseExcecao(String description) {
        super(description);
    }

    public BaseExcecao(String description, List<Error> erros) {
        super(description);
    }

    public BaseExcecao(List<RespostaHttp> respostasHttp) {
        this.setRespostasHttp(respostasHttp);
    }

    public List<RespostaHttp> getRespostasHttp() {
        return respostasHttp;
    }

    public void setRespostasHttp(List<RespostaHttp> respostasHttp) {
        this.respostasHttp = respostasHttp;
    }

}
