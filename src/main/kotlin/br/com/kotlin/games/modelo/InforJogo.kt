package br.com.alura.alugames.modelo

import br.com.alura.alugames.modelo.InfoApiShark

class InforJogo(val info: InfoApiShark){
    override fun toString(): String {
        return info.toString()
    }
}