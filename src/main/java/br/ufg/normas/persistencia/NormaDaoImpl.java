package br.ufg.normas.persistencia;

import br.ufg.normas.excecao.IdNaoValidoServiceException;
import br.ufg.normas.modelo.Norma;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    private Long idValido(Long id) {
        if (id <= 0) {
            throw new IdNaoValidoServiceException("Valor do campo id está invalido. Deve ser uma valor inteiro maior que zero.");
        }
        return id;
    }
}
