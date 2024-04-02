package br.com.alura.alugames.principal

import br.com.alura.alugames.dados.Banco
import br.com.alura.alugames.dados.JogosDAO
import org.example.br.com.alura.modelo.Jogo
import javax.persistence.EntityManager

fun main(){
    val manager = Banco.getEntityManager()
    val jogoDAO = JogosDAO(manager)
    val listaJogos:List<Jogo> = jogoDAO.getLista()

    println(listaJogos)
}