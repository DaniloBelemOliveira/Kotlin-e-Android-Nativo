package br.com.alura.alugames.utilitario

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.InfoApiShark
import br.com.alura.alugames.modelo.InfoGamerJson
import br.com.alura.alugames.modelo.InforJogo
import org.example.br.com.alura.modelo.Jogo

fun InfoGamerJson.criarGamer():Gamer{
    return Gamer(this.nome, this.email)
}

fun InfoApiShark.criarJogo(): Jogo {
    return Jogo(this.title,this.thumb,"",0.0)
}