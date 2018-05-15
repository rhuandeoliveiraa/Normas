
package br.ufg.normas.modelo;
import com.google.gson.annotations.Expose;

import br.ufg.normas.modelo.TipoRetorno;
import javolution.util.FastMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class RespostaHttp {
    @Expose
    private String codigo;

    @Expose
    private Map<String, String> parametros = new FastMap<>();

    @Expose
    private List<String> observacoes = new ArrayList<>();

    @Expose
    private TipoRetorno tipo = TipoRetorno.SUCESSO;

    @Expose
    private Object objeto;

    public RespostaHttp(String codigo){
        this.setCodigo(codigo);}

    public RespostaHttp(String codigo, TipoRetorno tipo){
        this.setCodigo(codigo);
        this.setTipo(tipo);}

    public RespostaHttp(){
        this.setParametros(getParametros());}

    public RespostaHttp(String codigo, Map<String, String> parametros){
        this.setCodigo(codigo);
        this.setParametros(parametros);}

    public RespostaHttp(String codigo, List<String> obs){
        this.setCodigo(codigo);
        this.setObservacoes(obs);}

    public RespostaHttp(String codigo, List<String> obs, TipoRetorno tipo){
        this.setCodigo(codigo);
        this.setObservacoes(obs);
        this.setTipo(tipo);}

    public RespostaHttp(String codigo, Object objeto){
        this.setCodigo(codigo);
        this.setObjeto(objeto);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Map<String, String> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, String>parametros) {
        this.parametros = parametros;
    }

    @Override
    public String toString() {
        return "{"+ getCodigo() +", Parametros: "+ getParametros() +"}";
    }

    public List<String> getObs() {
        return getObservacoes();
    }

    public List<String> getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(List<String> observacoes) {
        this.observacoes = observacoes;
    }

    public TipoRetorno getTipo() {
        return tipo;
    }

    public void setTipo(TipoRetorno tipo) {
        this.tipo = tipo;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }
}

