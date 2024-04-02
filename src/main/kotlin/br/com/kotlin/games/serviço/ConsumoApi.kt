package br.com.alura.alugames.serviço

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.InfoApiShark
import br.com.alura.alugames.modelo.InfoGamerJson
import br.com.alura.alugames.modelo.InforJogo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ConsumoApi {
    fun consomeDados(addr:String):String{
        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(addr))
            .build()

        val response = client
            .send(request, HttpResponse.BodyHandlers.ofString())

        val json = response.body()
        return json
    }

    fun buscaJogo( codigoId:String):InforJogo{
        var meuInfoJogo:InforJogo = InforJogo(InfoApiShark("",""))
        val resultado = runCatching {
            val addr = "https://www.cheapshark.com/api/1.0/games?id=$codigoId"
            val json = consomeDados(addr)
            val gson = Gson()
            meuInfoJogo = gson.fromJson(json, InforJogo::class.java)

        }

        resultado.onFailure {
            throw Exception("Codigo invalído!")
        }
        return meuInfoJogo
    }

    fun buscaGamers( ):List<Gamer>?{
        val addr = "https://raw.githubusercontent.com/jeniblodev/arquivosJson/main/gamers.json"
        val json = consomeDados(addr)
        val gson = Gson()
        val meuGamerTipo = object:TypeToken<List<InfoGamerJson>>() {}.type
        val listaInfoGamers:List<InfoGamerJson>? = gson.fromJson(json, meuGamerTipo)

        val listaGamersClasses = listaInfoGamers?.map{infoGamerJson -> Gamer(infoGamerJson.nome,infoGamerJson.email,
            infoGamerJson.dataNascimento,infoGamerJson.usuario) }
        return listaGamersClasses
    }
}