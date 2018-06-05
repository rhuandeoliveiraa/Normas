package br.ufg.normas.controlador;

import br.ufg.normas.excecao.NaoExisteDaoException;
import br.ufg.normas.excecao.NegocioExcecao;
import br.ufg.normas.modelo.*;
import br.ufg.normas.persistencia.IUsuarioDao;
import br.ufg.normas.utils.Validacao;
import br.ufg.normas.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/usuarios")

public class UsuarioController  {

    @Autowired
    private IUsuarioDao usuarioDao;

 // normas/usuarios/cadastrar/
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
            throw new NegocioExcecao(Collections.singletonList(new RespostaHttp("ME09",TipoRetorno.ERRO)));
        }

        //verificar se a senha é igual a confirmação de senha
        else if(!usuario.getSenha().equals(usuario.getConfirmacaoSenha())) {
            throw new NegocioExcecao(Collections.singletonList(new RespostaHttp("ME08",TipoRetorno.ERRO)));
        }

        //verificar se já existe email cadastrado
        else if (usuarioDao.numRegistros("email",usuario.getEmail(),String.class) == 1) {
            throw new NegocioExcecao(Collections.singletonList(new RespostaHttp("ME04_2", TipoRetorno.ERRO)));
        }


        //verificar se é o primeiro usuário
        else if(usuarioDao.numUsuarios() == 0){
            usuario.setDataCadastro(new Date());
            usuario.setDataInicioAdmin(new Date());
            usuario.setPapel(TipoPapel.ADMINISTRADOR);
            usuario.setSituacao(Situacao.ATIVO);
         }

        usuario.setDataCadastro(new Date());
        usuarioDao.salvar(usuario);


        return new RespostaHttp("MS01",usuario.getId());

    }
/*
    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public RespostaHttp logar(@RequestBody Usuario usuario){

       // usuarioDao.procurarPorLogin(usuario.getEmail());
         return new RespostaHttp("MS01");

    }
*/
    // normas/usuarios/editar/iddesejado
    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RespostaHttp editar(@PathVariable("id") Long id, @RequestBody Usuario usuario){
        //Busca no banco de dados a data de cadastro do usuário e já salva automaticamente quando o usuário for editado
        usuario.setDataCadastro(usuarioDao.buscarDataCadastro(id));

        usuarioDao.atualizar(id,usuario);
        return new RespostaHttp("MS01",usuario.getId());
    }

    // normas/usuarios/excluir/iddesejado
    @DeleteMapping("/excluir/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public RespostaHttp excluir(@PathVariable("id") Long id){
       if(usuarioDao.procurarPorId(id) == null){
            throw new NaoExisteDaoException(Collections.singletonList(new RespostaHttp("MA01", TipoRetorno.ALERTA)));
        }
        else {
            usuarioDao.deletar(id);
            return new RespostaHttp("MS01",id);
        }

    }

    // normas/usuarios/pesquisar/iddesejado
    @GetMapping("/pesquisar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RespostaHttp pesquisarUsuario(@PathVariable("id") Long id) {
        Usuario usuario = usuarioDao.procurarPorId(id);

        if(usuarioDao.procurarPorId(id) == null){
            throw new NaoExisteDaoException(Collections.singletonList(new RespostaHttp("MA01", TipoRetorno.ALERTA)));
        }
            else {

            return new RespostaHttp("MS01", usuario);
        }
    }

    // normas/usuarios/listar/

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public RespostaHttp listar() {
        List<Usuario> usuario = usuarioDao.procurarTodos();
        if (usuario.isEmpty()){
            return new RespostaHttp("MA01",TipoRetorno.ALERTA);
        }
        else {
            return new RespostaHttp("MS01", usuario);
        }
        }


}


