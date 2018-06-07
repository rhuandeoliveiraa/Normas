package br.ufg.normas.controlador;

import br.ufg.normas.excecao.NaoExisteDaoException;
import br.ufg.normas.excecao.NegocioExcecao;
import br.ufg.normas.modelo.Norma;
import br.ufg.normas.modelo.RespostaHttp;
import br.ufg.normas.modelo.TipoRetorno;
import br.ufg.normas.modelo.Usuario;
import br.ufg.normas.persistencia.INormaDao;
import br.ufg.normas.persistencia.NormaDaoImpl;
import br.ufg.normas.utils.Strings;
import br.ufg.normas.utils.Validacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/normas")

public class NormaController {

    @Autowired
    private INormaDao normaDao;

    @PostMapping("/cadastrar")
    public RespostaHttp salvar(@RequestBody Norma norma){
        norma.setDataCadastro(new Date());
        //verificar os campos obrigatórios
        if(Strings.isNullOrEmpty(norma.getNome()) || Strings.isNullOrEmpty(norma.getDescricao()) ) {
            throw  new NegocioExcecao(Collections.singletonList(new RespostaHttp("ME01",TipoRetorno.ERRO)));
        }

        //verificar se campo URL E campo arquivo estão nulos
        //OBS: FALTANDO A INCLUSÃO DO CAMPO ARQUIVO.
        if (Strings.isNullOrEmpty(norma.getUrl())){
            throw  new NegocioExcecao(Collections.singletonList(new RespostaHttp("ME17",TipoRetorno.ERRO)));

        }

        //verificar se já existe nome cadastrado
        else if (normaDao.numRegistros("nome",norma.getNome(),String.class) == 1) {
            throw new NegocioExcecao(Collections.singletonList(new RespostaHttp("ME15", TipoRetorno.ERRO)));
        }

        //verificar se a URL é  válida
        else if (!Validacao.validarURL(norma.getUrl())) {
            throw new NegocioExcecao(Collections.singletonList(new RespostaHttp("ME20",TipoRetorno.ERRO)));
        }

        normaDao.salvar(norma);
        return new RespostaHttp("MS01", norma.getId());
    }


    @GetMapping("/pesquisa/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public RespostaHttp pesquisarNormaPorNome(@PathVariable("nome ")String nome){
        List<Norma> norma = normaDao.pesquisarPorNome(nome);
        if (normaDao.pesquisarPorNome(nome) == null){
            throw new NaoExisteDaoException(Collections.singletonList(new RespostaHttp("MA01", TipoRetorno.ALERTA)));
        }
        else{
            return new RespostaHttp("MS01",norma);
        }

    }


    // normas/excluir/iddesejado
    @DeleteMapping("/excluir/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public RespostaHttp excluir(@PathVariable("id") Long id){
       if(normaDao.procurarPorId(id) == null){
            throw new NaoExisteDaoException(Collections.singletonList(new RespostaHttp("MA01", TipoRetorno.ALERTA)));
        }
        else {
            normaDao.deletar(id);
            return new RespostaHttp("MS01",id);
        }

    }

    // normas/usuarios/pesquisar/iddesejado
    @GetMapping("/pesquisar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RespostaHttp pesquisarNormaPorID(@PathVariable("id") Long id) {
        Norma norma = normaDao.procurarPorId(id);

        if(normaDao.procurarPorId(id) == null){
            throw new NaoExisteDaoException(Collections.singletonList(new RespostaHttp("MA01", TipoRetorno.ALERTA)));
        }
        else {

            return new RespostaHttp("MS01", norma);
        }
    }
/*
    @GetMapping("/pesquisar/{descricao}")
    @ResponseStatus(HttpStatus.OK)
    public RespostaHttp pesquisarNormaPorDescricao(@PathVariable("descricao")String descricao){
        List<Norma> norma = normaDao.pesquisarPorNome(nome);
        if (normaDao.pesquisarPorNome(nome) == null){
            throw new NaoExisteDaoException(Collections.singletonList(new RespostaHttp("MA01", TipoRetorno.ALERTA)));
        }
        else{
            return new RespostaHttp("MS01",norma);
        }

    }
    */

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public RespostaHttp listar() {
        List<Norma> normas = normaDao.procurarTodos();
        if (normas.isEmpty()){
            return new RespostaHttp("MA01",TipoRetorno.ALERTA);
        }
        else {
            return new RespostaHttp("MS01", normas);
        }

        //return normaDao.procurarTodos();
    }
}
