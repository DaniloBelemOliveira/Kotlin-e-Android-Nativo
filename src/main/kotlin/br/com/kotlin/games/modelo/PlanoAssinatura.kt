package br.com.alura.alugames.modelo

class PlanoAssinatura(
    id:Int,
    tipo:String,
    val mensalidade:Double,
    val jogosIncluido:Int,
    val percentualAPagar:Double
) :Plano(id,tipo){
    override fun obterValor(aluguel: AluguelJogo):Double {
        val totalJogosMes = aluguel.gamer.jogosDoMes(aluguel.periodo.dataInicial.monthValue) + 1
        return if (totalJogosMes <= jogosIncluido) {
            0.0
        } else {
            return if (aluguel.gamer.media >= 8) {
                -super.obterValor(aluguel) * percentualAPagar
            } else {
                -super.obterValor(aluguel)

            }
        }
    }
}