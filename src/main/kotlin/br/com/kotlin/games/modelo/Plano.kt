package br.com.alura.alugames.modelo


sealed  class Plano(var id:Int =0,
                    val tipo:String){

    open fun obterValor(aluguel:AluguelJogo):Double{
        return aluguel.jogo.preco * aluguel.periodo.emDias()
    }

    override fun toString(): String {
        return "Plano(id=$id, tipo='$tipo')"
    }

}
