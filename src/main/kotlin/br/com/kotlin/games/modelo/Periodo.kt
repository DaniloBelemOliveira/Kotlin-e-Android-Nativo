package br.com.alura.alugames.modelo

import java.time.LocalDate
import java.time.Period

data class Periodo(
    val dataInicial:LocalDate,
    val dataFinal:LocalDate
){
    fun emDias():Int{
        return Period.between(this.dataFinal,this.dataInicial).days
    }
}