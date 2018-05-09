package br.ufg.normas.persistencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Repository
@Transactional
@SuppressWarnings("unchecked")


public abstract class GenericDaoImpl<E extends Serializable,k > implements IGenericDao<E,k> {

    private final Class<E> entityClass;

    @PersistenceContext
    private EntityManager entityManager;

    public GenericDaoImpl(Class<E> entityClass) {

        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager() {

        return entityManager;
    }

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        entityClass = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public void salvar(E entity) {

        entityManager.persist(entity);

    }

    @Override
    public E atualizar(E entity) {

       return entityManager.merge(entity);
    }

    /*
    @Override
    public void deletar(E entity) {

        entityManager.remove(entity);

    }*/
    @Override
    public void deletar(k id){
        entityManager.remove(entityManager.getReference(entityClass,id));

    }

    @Override
    public E procurarPorId(k id) {
        return this.entityManager.find(entityClass,id);

    }


    @Override
    public List<E> procurarTodos() {
        return this.entityManager
                .createQuery("select x from " + this.entityClass.getSimpleName() + " x")
                .getResultList();



    }


    /*
    @Override
    public List<E> procurarTodos() {
        return this.entityManager
                .createQuery("select x from " + ().getSimpleName() + " x")
                .getResultList();



    }*/

}
