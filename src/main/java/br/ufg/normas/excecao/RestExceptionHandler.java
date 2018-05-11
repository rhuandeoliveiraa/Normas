package br.ufg.normas.excecao;

import br.ufg.normas.modelo.DetalheErro;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.sql.Timestamp;

@ControllerAdvice //trabalha como listener (ouvinte), sempre que uma exceção for lançada essa notacão irá capturá-la
/* Extendendo a classe  ResponseEntityExceptionHandler, pois ela fornece o método handleExceptionInternal, que será o responsável
   por enviar para aplicação cliente o objeto com a exceção tratada
 */
public class RestExceptionHandler  extends ResponseEntityExceptionHandler  {

    @ExceptionHandler({IdNaoValidoServiceException.class})
    public ResponseEntity<Object> idInvalido(IdNaoValidoServiceException ex,
                                             WebRequest request) {

        return handleExceptionInternal(
                ex, DetalheErro.builder()
                        .addErro(ex.getMessage())
                        .addStatus(HttpStatus.BAD_REQUEST)
                       // .addHttpMethod(getHttpMethod(request))
                        .addclasse(ex.getClass().getSimpleName())
                        .build(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }





    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> serverException(Exception ex, WebRequest request) {

        return handleExceptionInternal(
                ex, DetalheErro.builder()
                        .addTimestamp(new Timestamp(new Date().getTime()))
                        .addStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                      //  .addHttpMethod(getHttpMethod(request))
                        .addErro(ex.getMessage()) // 404, 500 etc etc
                        .addDetalhe(ex.getStackTrace()[0].getFileName() + ":"
                                + ex.getStackTrace()[0].getLineNumber() )
                        .addclasse(ex.getClass().getSimpleName())
                        .build(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({NegocioExcecao.class})
    public ResponseEntity<Object> negocioExceptionHandler(Exception ex, WebRequest request) {

        return handleExceptionInternal(
                ex, DetalheErro.builder()
                        .addTimestamp(new Timestamp(new Date().getTime()))
                        .addStatus(HttpStatus.PRECONDITION_FAILED)
                        //.addHttpMethod(getHttpMethod(request))
                        .addErro(ex.getMessage()) // 404, 500 etc etc
                        .addDetalhe(ex.getStackTrace()[0].getFileName() + ":"
                                + ex.getStackTrace()[0].getLineNumber())
                        .addclasse(ex.getClass().getSimpleName())
                        .build(),
                new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, request);
    }

    @ExceptionHandler({ConexaoExcecao.class})
    public ResponseEntity<Object> conexaoRecusadaExceptionHandler(Exception ex, WebRequest request) {

        return handleExceptionInternal(
                ex, DetalheErro.builder()
                        .addTimestamp(new Timestamp(new Date().getTime()))
                        .addStatus(HttpStatus.SERVICE_UNAVAILABLE)
                       // .addHttpMethod(getHttpMethod(request))
                        .addErro(ex.getMessage()) // 404, 500 etc etc
                        .addDetalhe(ex.getStackTrace()[0].getFileName() + ":"
                                + ex.getStackTrace()[0].getLineNumber() )
                        .addclasse(ex.getClass().getSimpleName())
                        .build(),
                new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }

    @ExceptionHandler({NaoExisteDaoException.class})
    public ResponseEntity<Object> entidadeNaoEncontrada(NaoExisteDaoException ex, WebRequest request) {

        return handleExceptionInternal(
                ex, DetalheErro.builder()
                        .addDetalhe("Recurso não encontrado na base de dados.")
                        .addErro(ex.getMessage())
                        .addStatus(HttpStatus.NOT_FOUND)
                        .addHttpMethod(getHttpMethod(request))
                        .addclasse(getPath(request))
                        .build(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    /*

    @ExceptionHandler({org.hibernate.PropertyValueException.class})
    public ResponseEntity<Object> propriedadeNula(org.hibernate.PropertyValueException ex, WebRequest request) {

        return handleExceptionInternal(
                ex, DetalheErro.builder()
                        //.addDetalhe("O atributo '"+ ex.getPropertyName() +"' não pode ser nulo.")
                        .addDetalhe(ex.toString()+ "(" +ex.getStackTrace()[0].getFileName() + ":"
                                    + ex.getStackTrace()[0].getLineNumber() + ")")
                        .addErro(ex.getMessage())
                        .addStatus(HttpStatus.BAD_REQUEST)
                        .addHttpMethod(getHttpMethod(request))
                        .addclasse(ex.getClass().getSimpleName())
                        //.addclasse(getPath(request))
                        .addTimestamp(new Timestamp(new Date().getTime()))
                        .build(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

     @ExceptionHandler({org.hibernate.exception.ConstraintViolationException.class})
    public ResponseEntity<Object> constraintViolada(org.hibernate.exception.ConstraintViolationException ex,
                                                    WebRequest request) {

        return handleExceptionInternal(
                ex, DetalheErro.builder()
                        .addDetalhe("Constraint violada: " + ex.getConstraintName())
                        .addErro(ex.getMessage())
                        .addStatus(HttpStatus.CONFLICT)
                        .addHttpMethod(getHttpMethod(request))
                        .addclasse(getPath(request))
                        .build(),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class}) //quando alguma dessas duas exceções foram lançadas, essa classe
    //irá capturá-la
    public ResponseEntity<Object> serverException(RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(
                ex, DetalheErro.builder()
                        .addDetalhe("Um exceção foi lançada.")
                        .addErro(ex.getMessage())
                        .addStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .addHttpMethod(getHttpMethod(request))
                        .addclasse(getPath(request))
                        .build(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
*/


    private String getPath(WebRequest request) { //recupera URI q fez a requisição ao webservice

        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }

    private String getHttpMethod(WebRequest request) { //recuperar tipo de método HTTP

        return ((ServletWebRequest) request).getRequest().getMethod();
    }

}

