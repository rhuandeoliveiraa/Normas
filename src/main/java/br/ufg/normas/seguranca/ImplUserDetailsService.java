package br.ufg.normas.seguranca;

import br.ufg.normas.excecao.NegocioExcecao;
import br.ufg.normas.modelo.RespostaHttp;
import br.ufg.normas.modelo.TipoRetorno;
import br.ufg.normas.modelo.Usuario;
import br.ufg.normas.persistencia.IUsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;


import java.util.Collections;

@Repository

public class ImplUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.procurarPorLogin(email);
        if (usuario == null) {
            throw new NegocioExcecao(Collections.singletonList(new RespostaHttp("ME13", TipoRetorno.ERRO)));
        }
        return usuario;
    }


}

