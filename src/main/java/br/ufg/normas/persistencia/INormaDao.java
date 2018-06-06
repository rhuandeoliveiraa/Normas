package br.ufg.normas.persistencia;

import br.ufg.normas.modelo.Norma;


import java.util.List;

public interface INormaDao extends IGenericDao<Norma,Long> {

    void salvar(Norma norma);

    void atualizar(Long id, Norma norma);

    void deletar(Long id);

    Norma procurarPorId(Long id);

    List<Norma> procurarTodos();

    Long numRegistros(String attb, String value, Class classe);

    List<Norma> pesquisarPorNome(String nome);
}
