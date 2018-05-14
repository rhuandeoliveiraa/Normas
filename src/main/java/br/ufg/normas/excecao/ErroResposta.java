package br.ufg.normas.excecao;

import br.ufg.normas.modelo.RespostaHttp;
import com.google.gson.annotations.Expose;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.List;

public class ErroResposta {

    @Expose
    private String description;

    @Expose
    private Timestamp timestamp;

    @Expose
    private HttpStatus status;

    @Expose
    private String classException;

    @Expose
    private List<RespostaHttp> respostasHttp;

    @Expose
    private String exception;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getClassException() {
        return classException;
    }

    public void setClassException(String classException) {
        this.classException = classException;
    }

    public List<RespostaHttp> getRespostasHttp() {
        return respostasHttp;
    }

    public void setRespostasHttp(List<RespostaHttp> respostasHttp) {
        this.respostasHttp = respostasHttp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Description: "+ getDescription() +
                ", timestamp: "+ getTimestamp() +
                ", status: "+ getStatus() +
                ", ClassException: "+ getClassException();
        /*", Errors: "+erros.stream().map(Object::toString).collect(Collectors.joining(", "));*/
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}

