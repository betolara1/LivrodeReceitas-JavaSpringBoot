/* 
    No Repository é onde eu pego os dados do banco de dados
    Crio minhas funções para retornar os dados que quiser
*/

package com.roberto.Livro_de_Receitas.repository;

import org.springframework.stereotype.Repository;
import com.roberto.Livro_de_Receitas.model.ReceitasDB;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ReceitasRepository extends JpaRepository<ReceitasDB, Long> {

}
