package br.ufg.normas.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.util.Date;

@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> genericExceptionHandler(Exception ex) {

        ErroResposta errorResponse = new ErroResposta();
        errorResponse.setTimestamp(new Timestamp(new Date().getTime()));
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setClassException(ex.getClass().getName());
        errorResponse.setException(ex.toString() + "(" + ex.getStackTrace()[0].getFileName() + ":" + ex.getStackTrace()[0].getLineNumber()+")");

        System.out.println(ANSI_RED+errorResponse+ANSI_RESET);
        ex.printStackTrace();

        return new ResponseEntity<Object>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NegocioExcecao.class)
    public ResponseEntity<?> negocioExceptionHandler(Exception ex) {

        ErroResposta erroResposta = new ErroResposta();
        erroResposta.setTimestamp(new Timestamp(new Date().getTime()));
        erroResposta.setStatus(HttpStatus.PRECONDITION_FAILED);
        erroResposta.setClassException(ex.getClass().getName());
        erroResposta.setRespostasHttp(((NegocioExcecao) ex).getRespostasHttp());

        System.out.println(ANSI_YELLOW+erroResposta+ANSI_RESET);

        return new ResponseEntity<Object>(erroResposta, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(ConexaoExcecao.class)
    public ResponseEntity<?> conexaoRecusadaExceptionHandler(Exception ex) {

        ErroResposta erroResposta = new ErroResposta();
        erroResposta.setTimestamp(new Timestamp(new Date().getTime()));
        erroResposta.setDescription(ex.getMessage());
        erroResposta.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
        erroResposta.setRespostasHttp(((ConexaoExcecao) ex).getRespostasHttp());
        erroResposta.setClassException(ex.getClass().getName());

        System.out.println(ANSI_RED+erroResposta+ANSI_RESET);

        return new ResponseEntity<Object>(erroResposta, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(NaoExisteDaoException.class)
    public ResponseEntity<?> naoEncontradoExceptionHandler(Exception ex) {

        ErroResposta erroResposta = new ErroResposta();
        erroResposta.setTimestamp(new Timestamp(new Date().getTime()));
        erroResposta.setDescription(ex.getMessage());
        erroResposta.setStatus(HttpStatus.NOT_FOUND);
        erroResposta.setRespostasHttp(((NaoExisteDaoException) ex).getRespostasHttp());
        erroResposta.setClassException(ex.getClass().getName());

        System.out.println(ANSI_RED+erroResposta+ANSI_RESET);

        return new ResponseEntity<Object>(erroResposta, HttpStatus.NOT_FOUND);
    }

}

