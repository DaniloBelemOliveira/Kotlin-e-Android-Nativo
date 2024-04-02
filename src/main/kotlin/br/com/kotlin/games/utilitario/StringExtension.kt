package br.com.alura.alugames.utilitario

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun String.transformaEmIdade(): Int{
    val formater = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val dataNascimento = LocalDate.parse(this,formater)
    val age = Period.between(dataNascimento,LocalDate.now()).years
    return  age
}