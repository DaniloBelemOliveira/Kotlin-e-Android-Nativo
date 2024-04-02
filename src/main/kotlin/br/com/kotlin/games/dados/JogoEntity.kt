package br.com.alura.alugames.dados

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "jogos")
data class JogoEntity (
    val titulo: String = "Titulo Do Jogo",
    val capa:String = "Capa Do Jogo",
    val descricao:String = "",
    val preco:Double = 0.0,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id:Int = 0)
