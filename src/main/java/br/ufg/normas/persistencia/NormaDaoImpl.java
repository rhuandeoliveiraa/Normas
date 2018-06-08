package br.ufg.normas.persistencia;

import br.ufg.normas.excecao.IdNaoValidoServiceException;
import br.ufg.normas.modelo.Norma;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
@Transactional
public class NormaDaoImpl extends GenericDaoImpl<Norma,Long> implements INormaDao{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void salvar(Norma norma) {

        super.salvar(norma);
    }

    @Override
    public void atualizar(Long id, Norma norma) {
        norma.setId(idValido(id));
        super.procurarPorId(id);
        super.atualizar(norma);
    }

    @Override
    public void deletar(Long id) {

        super.deletar(id);
    }


    @Override
    @Transactional(readOnly = true)
    public Norma procurarPorId( Long id) {

        return super.procurarPorId(idValido(id));
    }




    @Override
    @Transactional(readOnly = true)
    public List<Norma> procurarTodos() {

        return super.procurarTodos();
    }



    //Retorna o número de registros presentes no banco de dados dependendo do atributo que for passado
    public Long numRegistros(String attb, String value, Class classe){
        Boolean isString = classe == String.class;
        String comparador = isString ? "LIKE" : "=";

        Query jpql = this.entityManager.createQuery("select count(*) from Norma where " + attb + " " + comparador + " ?1");


        if(isString){
            jpql.setParameter(1,value);

        } else {
            jpql.setParameter(1,Long.parseLong(value));

        }
        Long count = (Long) jpql.getSingleResult();
        return count;


    }

    public List<Norma> pesquisarPorNome(String nome){
        Query query = this.entityManager.createQuery("select nome from Norma where nome like %?1%");
        query.setParameter(1,nome);
        return  query.getResultList();

    }



    private Long idValido(Long id) {
        if (id <= 0) {
            throw new IdNaoValidoServiceException("Valor do campo id está invalido. Deve ser uma valor inteiro maior que zero.");
        }
        return id;
    }
}
