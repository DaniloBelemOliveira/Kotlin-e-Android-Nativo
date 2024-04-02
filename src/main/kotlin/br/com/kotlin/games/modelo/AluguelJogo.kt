package br.com.alura.alugames.modelo


import org.example.br.com.alura.modelo.Jogo

class AluguelJogo(
    val gamer:Gamer
    ,val jogo: Jogo,
    val periodo: Periodo )
{
    val valorAPagar = gamer.plano.obterValor(this)
    override fun toString(): String {
        return "Aluguel do jogo '${jogo.titulo}', por ${gamer.nome}, pelo preço de ${valorAPagar} R$"
    }
}