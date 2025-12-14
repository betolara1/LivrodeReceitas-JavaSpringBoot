/*
    Aqui crio as regras de negócio do sistema

*/

package com.roberto.Livro_de_Receitas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.roberto.Livro_de_Receitas.model.ReceitasDB;
import com.roberto.Livro_de_Receitas.repository.ReceitasRepository;

@Service
public class ReceitasService {
    private final ReceitasRepository receitasRepository;

    public ReceitasService (ReceitasRepository receitasRepository){
        this.receitasRepository = receitasRepository;
    }

    public List<ReceitasDB> listarReceitas(){
        return receitasRepository.findAll();
    }
}
