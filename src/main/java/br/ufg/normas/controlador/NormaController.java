package br.ufg.normas.controlador;

import br.ufg.normas.modelo.Norma;
import br.ufg.normas.modelo.RespostaHttp;
import br.ufg.normas.modelo.TipoRetorno;
import br.ufg.normas.persistencia.INormaDao;
import br.ufg.normas.persistencia.NormaDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        normaDao.salvar(norma);
        return new RespostaHttp("MS01", norma.getId());
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public RespostaHttp listar() {
        List<Norma> usuario = normaDao.procurarTodos();
        if (usuario.isEmpty()){
            return new RespostaHttp("MA01",TipoRetorno.ALERTA);
        }
        else {
            return new RespostaHttp("MS01", usuario);
        }

        //return normaDao.procurarTodos();
    }
}
