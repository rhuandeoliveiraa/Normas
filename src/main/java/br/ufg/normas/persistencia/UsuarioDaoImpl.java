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

                System.out.println(email);

                Query query = this.entityManager
                .createQuery("select u from Usuario u where u.email = :emailcadastrado");
                query.setParameter("emailcadastrado" ,email);
                Usuario us =  (Usuario)query.getSingleResult();

        if (us == null){
            //Não cadastrado
            return false;
        }
        else {
            // Já cadastrado
            return true;
        }

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

    private Long idValido(Long id) {
        if (id <= 0) {
            throw new IdNaoValidoServiceException("Valor do campo id está invalido. Deve ser uma valor inteiro maior que zero.");
        }
        return id;
    }
}
