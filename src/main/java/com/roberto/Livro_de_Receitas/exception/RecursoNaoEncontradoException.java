package com.roberto.Livro_de_Receitas.exception;

public class RecursoNaoEncontradoException extends RuntimeException{

    // CONSTRUTOR PARA RETORNAR A MENSAGEM DE ERRO 
    public RecursoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
