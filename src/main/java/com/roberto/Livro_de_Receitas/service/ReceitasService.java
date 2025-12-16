/*
    Aqui crio as regras de negócio do sistema

*/

package com.roberto.Livro_de_Receitas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.roberto.Livro_de_Receitas.exception.RecursoNaoEncontradoException;
import com.roberto.Livro_de_Receitas.model.ReceitasDB;
import com.roberto.Livro_de_Receitas.repository.ReceitasRepository;

import ch.qos.logback.core.joran.conditional.IfAction;

@Service
public class ReceitasService {

    // INSTANCIAR A CLASSE DE RECEITASREPOSITORY
    private final ReceitasRepository receitasRepository;
    public ReceitasService (ReceitasRepository receitasRepository){
        this.receitasRepository = receitasRepository;
    }

    //CLASSE PARA LISTAR TODOS OS DADOS DA TABELA RECEITAS
    public List<ReceitasDB> listarReceitas(){
        return receitasRepository.findAll();
    }

    //CLASSE QUE BUSCA OS DADOS POR ID
    public ReceitasDB buscarPorId(Long id){
        return receitasRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException
                ("Receita com ID "+id+" não encontrada!")); //CRIO A EXCEÇÃO SE NÃO ENCONTRAR A RECEITA
    }

    //CLASSE PARA SALVAR AS RECEITAS
    public ReceitasDB salvarReceita(ReceitasDB receita){
        return receitasRepository.save(receita);
    }

    //CLASSE PARA DELETAR UMA RECEITA
    public void deletarReceita(Long id){

        if(!receitasRepository.existsById(id)){
            throw new RecursoNaoEncontradoException("Receita com o ID "+id+" não encontrado!");
        }

        receitasRepository.deleteById(id);
    }

}
