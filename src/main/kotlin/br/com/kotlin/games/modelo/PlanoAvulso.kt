package br.com.alura.alugames.modelo

class PlanoAvulso (id:Int =0,tipo:String):Plano(id,tipo)
{
     override
     fun obterValor(aluguel:AluguelJogo):Double{
        var valorOriginal = super.obterValor(aluguel)
        return if(aluguel.gamer.media > 8){
            valorOriginal*0.9
        }
        else{
            valorOriginal
        }
    }
}