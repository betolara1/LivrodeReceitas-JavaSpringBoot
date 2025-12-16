package com.roberto.Livro_de_Receitas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roberto.Livro_de_Receitas.model.ReceitasDB;
import com.roberto.Livro_de_Receitas.service.ReceitasService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


//GET É PARA RETORNAR AS INFORMAÇÕES (CONSULTA)
//POST É PARA INSERIR AS INFORMAÇÕES (SALVAR)
//PUT É PARA ALTERAR AS INFORMAÇÕES (ALTERAR)
//DELETE É PARA DELETAR AS INFORMAÇÕES (DELETAR)

@RestController
@RequestMapping("/api/receitas")
public class ReceitasController {

    //CRIAR O CONSTRUTOR PRA INSTANCIAR A CLASSE DO RECEITAS SERVICE
    private final ReceitasService receitasService;
    public ReceitasController(ReceitasService receitasService){
        this.receitasService = receitasService;
    } 

    //CLASSE PARA LISTAR AS RECEITAS 
    @GetMapping
    public List<ReceitasDB> listarReceitas() {
        return receitasService.listarReceitas();
    }

    //CLASSE PARA RETORNAR A RECEITA PELO ID
    //SEMPRE COLOCAR O ? DENTRO DO <> QUANDO COLOCAR TRATAMENTO DE EXCEÇÃO - ResponseEntity<?>
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarReceitaId(@PathVariable Long id) { 
        //TRY E CATCH NÃO É MAIS NECESSARIO PORQUE CRIAMOS O ARQUIVO DE EXCEÇÕES GLOBAIS
        ReceitasDB receitasDB = receitasService.buscarPorId(id);
        return ResponseEntity.ok(receitasDB);
    }
    
    //CLASSE PARA CRIAR AS RECEITAS
    @PostMapping
    public ReceitasDB criarReceita(@RequestBody ReceitasDB receita) {
        return receitasService.salvarReceita(receita);
    }
    
    //CLASSE PARA DELETAR RECEITAS POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReceita(@PathVariable Long id){
        receitasService.deletarReceita(id);
        return ResponseEntity.noContent().build(); //USADO PORQUE O DELETARRECEITA CRIADO COMO VOID 
    }
}
