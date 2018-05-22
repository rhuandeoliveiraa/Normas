package br.ufg.normas.persistencia;

import br.ufg.normas.modelo.Usuario;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import java.util.List;

//@Repository

public interface IUsuarioDao extends IGenericDao<Usuario,Long>  {

    void salvar(Usuario usuario);

    void atualizar(Long id, Usuario usuario);

    void deletar(Long id);

    Usuario procurarPorId(Long id);

    List<Usuario> procurarTodos();

    Long numRegistros(String attb, String value, Class classe);

    Long numUsuarios();


}
