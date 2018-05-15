package br.ufg.normas.controlador;

import br.ufg.normas.excecao.NegocioExcecao;
import br.ufg.normas.modelo.RespostaHttp;
import br.ufg.normas.modelo.Situacao;
import br.ufg.normas.modelo.TipoRetorno;
import br.ufg.normas.modelo.Usuario;
import br.ufg.normas.persistencia.IUsuarioDao;
import br.ufg.normas.service.Validacao;
import br.ufg.normas.utils.Strings;
import org.codehaus.plexus.util.FastMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;


@RestController
@RequestMapping(value = "/usuarios")


public class UsuarioController  {


    @Autowired
    private IUsuarioDao usuarioDao;



   // normas/usuarios/cadastrar/
    @CrossOrigin(origins = "http://192.168.1.120:9090")
    @PostMapping("/cadastrar")
    public RespostaHttp salvar(@RequestBody Usuario usuario) {


        //verificar os campos obrigatórios
        if(Strings.isNullOrEmpty(usuario.getNome()) || Strings.isNullOrEmpty(usuario.getSobrenome()) ||
           Strings.isNullOrEmpty(usuario.getEmail() )|| Strings.isNullOrEmpty(usuario.getSenha())) {
            throw  new NegocioExcecao(Collections.singletonList(new RespostaHttp("ME01",TipoRetorno.ERRO)));
        }

        //verificar se email e senha são válidos
        else if (!Validacao.validarEmail(usuario.getEmail()) ||
                !Validacao.validarSenha(usuario.getSenha())) {
            throw new NegocioExcecao(Collections.singletonList(new RespostaHttp("ME09",TipoRetorno.ERRO)));}

        //verificar se a senha é igual a confirmação de senha
        else if(!usuario.getSenha().equals(usuario.getConfirmacaoSenha())) {
            throw new NegocioExcecao(Collections.singletonList(new RespostaHttp("ME08",TipoRetorno.ERRO)));

        }
         /*
        //verificar se já existe email cadastrado
        else if (usuarioDao.numRegistros("email",usuario.getEmail(),String.class) == 1)
            throw new NegocioExcecao(Collections.singletonList(new RespostaHttp("ME04_2")));


        else if (usuarioDao.existeEmail(usuario.getEmail())){
            throw new NegocioExcecao(Collections.singletonList(new RespostaHttp("ME04_2")));
        }*/

        usuario.setDataCadastro(new Date());

        usuario.setDataInicioAdmin(new Date());
        //usuario.setDataFimAdmin(new Date());
        usuario.setSituacao(Situacao.ATIVO);
        usuarioDao.salvar(usuario);

        return new RespostaHttp("MS01",usuario.getId());

    }





    // normas/usuarios/excluir/iddesejado
    @DeleteMapping("/excluir/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable("id") Long id){

        usuarioDao.deletar(id);
    }

    // normas/usuarios/editar/iddesejado
    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario editar(@PathVariable("id") Long id, @RequestBody Usuario usuario){
        usuarioDao.atualizar(id,usuario);
        return usuario;
    }

    // normas/usuarios/pesquisar/iddesejado
    @GetMapping("/pesquisar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario pesquisarUsuario(@PathVariable("id") Long id) {

        return usuarioDao.procurarPorId(id);
    }

    // normas/usuarios/listar

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> listar() {

        return usuarioDao.procurarTodos();
    }


}


