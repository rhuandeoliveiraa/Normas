package br.ufg.normas.persistencia;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public interface IGenericDao<E extends Serializable,k> {
    void salvar(E entity);
    E atualizar(E entity);
    void deletar(k id);
     E procurarPorId(k id);
     List<E> procurarTodos();


}
