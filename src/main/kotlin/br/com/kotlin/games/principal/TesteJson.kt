package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Periodo
import br.com.alura.alugames.modelo.PlanoAssinatura
import br.com.alura.alugames.serviço.ConsumoApi
import br.com.alura.alugames.utilitario.criarJogo
import com.google.gson.GsonBuilder
import java.io.File
import java.time.LocalDate

fun main(){
    val consumo = ConsumoApi()
    val listaGamers = consumo.buscaGamers()
    val jogoInfo = consumo.buscaJogo("151")
    val jogo = jogoInfo.info.criarJogo()
    jogo.preco = 10.0


    val periodo1 = Periodo(LocalDate.now(),LocalDate.now().plusDays(7))


    val jogo2 = consumo.buscaJogo("82").info.criarJogo()
    jogo2.preco = 900.0
    val periodo2 = Periodo(LocalDate.now(),LocalDate.now().plusDays(15))


    val gamer2 = listaGamers?.get(3)
    val gamer3 = listaGamers?.get(4)
    //gamer2?.plano = PlanoAssinatura("Platina",9.9 ,3)
    val periodo3 = Periodo(LocalDate.now(),LocalDate.now().plusDays(9))
    val jogo3 = consumo.buscaJogo("215").info.criarJogo()
    jogo3.preco = 12.1

    gamer2?.recomendar(7)
    gamer2?.recomendar(10)

    gamer2?.alugaJogo(jogo3,periodo3)
    gamer2?.alugaJogo(jogo3,periodo3)
    gamer2?.alugaJogo(jogo3,periodo3)
    gamer2?.alugaJogo(jogo3,periodo3)
    println(gamer2?.jogosAlugados)

    gamer2?.recomendarJogo(jogo2,9)
    gamer3?.recomendarJogo(jogo2,8)

    println(gamer2?.jogosRecomendadedos.toString())

    val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    val serializacao = gson.toJson(gamer2?.jogosRecomendadedos)
    val arquivo = File("${gamer2?.nome}-JogosRecomendadados.json")
    arquivo.writeText(serializacao)
    // O codigo logo abaixo sinaliza o caminho do arquivo
    println(arquivo.absolutePath)
}