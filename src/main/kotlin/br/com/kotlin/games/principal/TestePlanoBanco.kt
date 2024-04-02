package br.com.alura.alugames.principal

import br.com.alura.alugames.dados.Banco
import br.com.alura.alugames.dados.PlanosDAO
import br.com.alura.alugames.modelo.Plano
import br.com.alura.alugames.modelo.PlanoAvulso

fun main(){
    val planoTerra = PlanoAvulso(0,"terra")

    val manager = Banco.getEntityManager()
    val planosDAO = PlanosDAO(manager)

    println(planosDAO.toEntity(planoTerra))

    planosDAO.getLista().forEach{
        println(it.toString())
    }

    manager.close()
}