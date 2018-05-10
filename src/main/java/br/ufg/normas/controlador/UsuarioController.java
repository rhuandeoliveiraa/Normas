package br.ufg.normas.controlador;

import br.ufg.normas.modelo.Usuario;

//import br.ufg.normas.persistencia.IGenericDao;
import br.ufg.normas.persistencia.IUsuarioDao;
//import br.ufg.normas.persistencia.UsuarioDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")


public class UsuarioController  {

    @Qualifier("usuariodao")
    @Autowired
    private IUsuarioDao dao;
    teste

   // normas/usuarios/salvar/
    @PostMapping("/salvar")
    public Usuario salvar(@RequestBody Usuario usuario) {

        dao.salvar(usuario);
        return usuario;
    }

    // normas/usuarios/excluir/iddesejado
    @DeleteMapping("/excluir/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable("id") Long id){

        dao.deletar(id);
    }

    // normas/usuarios/editar/iddesejado
    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario editar(@PathVariable("id") Long id, @RequestBody Usuario usuario){
        dao.atualizar(id,usuario);
        return usuario;
    }

    // normas/usuarios/pesquisar/iddesejado
    @GetMapping("/pesquisar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario pesquisarUsuario(@PathVariable("id") Long id) {

        return dao.procurarPorId(id);
    }

    // normas/usuarios/listar

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> listar() {

        return dao.procurarTodos();
    }


}


