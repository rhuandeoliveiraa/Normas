package br.ufg.normas.modelo;


import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.sql.Timestamp;


public class DetalheErro implements Serializable {

    //codigo do status (404,503 etc)
    private Integer Codigo;

    //msg referente ao codigo do status ( 404 = not found etc)
    private String mensagem;

    //dizer ao cliente qual tipo de método http que esse solicitação foi enviada
    private String httpMethod;

    // pegar a propria msg de erro que uma exceção lançou ou escrever a propria
    private String erro;

    private String detalhe;

    //serve para retornar a URL que fez a solicitação
    private String classe;

    private Timestamp timestamp;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getCodigo() {

        return Codigo;
    }

    public String getmensagem() {

        return mensagem;
    }

    public String getHttpMethod() {

        return httpMethod;
    }

    public String getErro() {

        return erro;
    }

    public String getDetalhe() {

        return detalhe;
    }

    public String getclasse() {

        return classe;
    }

    //vai ser utilizado para que se possa criar o objeto builder
    public static Builder builder() {

        return new Builder();
    }

    //assinatura da classe
    public static class Builder {

        //variável que sera utilizada para inserir os valores na criacao de um detalhe erro
        private DetalheErro erro;

        Builder() {
            this.erro = new DetalheErro();
        }

        public Builder addStatus(HttpStatus status) {
            this.erro.Codigo = status.value();
            this.erro.mensagem = status.getReasonPhrase();
            return this;
        }

        public Builder addHttpMethod(String method) {
            this.erro.httpMethod = method;
            return this;
        }

        public Builder addErro(String erro) {
            this.erro.erro = erro;
            return this;
        }

        public Builder addDetalhe(String detalhe) {
            this.erro.detalhe = detalhe;
            return this;
        }

        public Builder addclasse(String classe) {
            this.erro.classe = classe;
            return this;
        }

        public Builder addTimestamp(Timestamp timestamp){
             this.erro.timestamp = timestamp;
             return this;
        }

        public DetalheErro build() {

            return this.erro;
        }


    }

    @Override
    public String toString() {
        return "DetalheErro{" +
                "Codigo=" + Codigo +
                ", mensagem='" + mensagem + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                ", erro='" + erro + '\'' +
                ", detalhe='" + detalhe + '\'' +
                ", classe='" + classe + '\'' +
                '}';
    }
}