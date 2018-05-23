 package br.ufg.normas.persistencia;

 import br.ufg.normas.excecao.IdNaoValidoServiceException;
 import br.ufg.normas.modelo.Usuario;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.stereotype.Repository;
 import org.springframework.transaction.annotation.Transactional;

 import javax.persistence.EntityManager;
 import javax.persistence.PersistenceContext;
 import javax.persistence.Query;
 import java.util.List;

@SuppressWarnings("unchecked")
@Repository
@Transactional
//@Qualifier("usuariodao")
public class UsuarioDaoImpl  extends GenericDaoImpl<Usuario,Long> implements IUsuarioDao {

    @PersistenceContext
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


    public Long numRegistros(String attb, String value, Class classe){
        Boolean isString = classe == String.class;
        String comparador = isString ? "LIKE" : "=";

        Query jpql = this.entityManager.createQuery("select count(*) from Usuario where " + attb + " " + comparador + " ?1");


        if(isString){
            jpql.setParameter(1,value);

        } else {
            jpql.setParameter(1,Long.parseLong(value));

             }
        Long count = (Long) jpql.getSingleResult();
        return count;


    }

    public Long numUsuarios(){
        Query query = this.entityManager.createQuery("SELECT count(*) FROM Usuario ");
        Long count = (Long) query.getSingleResult();
        System.out.println("numero de usuarios: " +count);
        return count;
    }

    private Long idValido(Long id) {
        if (id <= 0) {
            throw new IdNaoValidoServiceException("Valor do campo id está invalido. Deve ser uma valor inteiro maior que zero.");
        }
        return id;
    }
}
