package br.com.alura.alugames.dados

import org.example.br.com.alura.modelo.Jogo
import javax.persistence.EntityManager

class JogosDAO (override val manager:EntityManager): DAO<Jogo,JogoEntity>(manager,JogoEntity::class.java) {

    override fun toModel(objeto: JogoEntity): Jogo {
        return Jogo(objeto.titulo,objeto.capa,objeto.descricao,objeto.preco,objeto.id)
    }

    override fun toEntity(objeto: Jogo): JogoEntity {
        return  JogoEntity(objeto.titulo,objeto.capa,objeto.descricao, objeto.preco)
    }

    fun commitGeneralista() {
        val listaJogos: List<Jogo> = getLista()
        listaJogos.forEach{ adicionar(it) }
    }

}

