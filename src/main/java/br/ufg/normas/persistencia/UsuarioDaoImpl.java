 package br.ufg.normas.persistencia;

import br.ufg.normas.excecao.IdNaoValidoServiceException;
import br.ufg.normas.excecao.NaoExisteDaoException;
import br.ufg.normas.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
@Transactional
@Qualifier("usuariodao")
public class UsuarioDaoImpl  extends GenericDaoImpl<Usuario,Long> implements IUsuarioDao {




    @Override
    public void salvar(Usuario usuario) {

         super.salvar(usuario);
    }

    @Override
    public void atualizar(Long id, Usuario usuario) {
       usuario.setId(idValido(id));
       super.procurarPorId(id);
       super.atualizar(usuario);
    }

    @Override
    public void deletar(Long id) {
        super.deletar(idValido(id));
        super.deletar(id);
    }


    @Override
    @Transactional(readOnly = true)
    public Usuario procurarPorId( Long id) {

        return super.procurarPorId(idValido(id));
    }


    @Override
    @Transactional(readOnly = true)
    public List<Usuario> procurarTodos() {
       return super.procurarTodos();
    }

    private Long idValido(Long id) {
        if (id <= 0) {
            throw new IdNaoValidoServiceException("Valor do campo id está invalido. Deve ser uma valor inteiro maior que zero.");
        }
        return id;
    }
}
