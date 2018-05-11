 package br.ufg.normas.persistencia;

 import br.ufg.normas.excecao.IdNaoValidoServiceException;
 import br.ufg.normas.modelo.Usuario;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.stereotype.Repository;
 import org.springframework.transaction.annotation.Transactional;

 import javax.persistence.EntityManager;
 import javax.persistence.Query;
 import java.util.List;

@SuppressWarnings("unchecked")
@Repository
@Transactional
@Qualifier("usuariodao")
public class UsuarioDaoImpl  extends GenericDaoImpl<Usuario,Long> implements IUsuarioDao {

    private EntityManager entityManager;


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

    @Override
    public boolean existeEmail(String email) {
                this.entityManager
                .createQuery("select u from USUARIO u where u.email = ?1")
                .setParameter(1,email)
                .getSingleResult();

        if (existeEmail(email)){
            return true;
        }
        else return false;

    }

    public Long numRegistros(String attb, String value, Class classe){
        Boolean isString = classe == String.class ? true : false;
        String comparador = isString ? "LIKE" : "=";

        Query jpql = entityManager.createQuery("select count(*) from Usuario where " + attb + " " + comparador + " :1");


        if(isString){
            jpql.setParameter(1,value);

        } else {
            jpql.setParameter(1,Long.parseLong(value));

             }
        Long count = (Long) jpql.getSingleResult();
        return count;


    }

    private Long idValido(Long id) {
        if (id <= 0) {
            throw new IdNaoValidoServiceException("Valor do campo id está invalido. Deve ser uma valor inteiro maior que zero.");
        }
        return id;
    }
}
