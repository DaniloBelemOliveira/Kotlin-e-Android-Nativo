package br.com.alura.alugames.principal
import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.serviço.ConsumoApi
import br.com.alura.alugames.utilitario.criarJogo
import org.example.br.com.alura.modelo.Jogo
import br.com.alura.alugames.utilitario.transformaEmIdade
import java.util.Scanner

fun main() {
    val scan = Scanner(System.`in`)

    val gamer = Gamer.criarGamer(scan)
    println(gamer.toString())
    println("Idade do Gamer: "+ gamer.dataNascimento?.transformaEmIdade())
    println("Gamer criado com sucesso!")

    do {
        print("Digite um codigo de jogo para buscar: ")
        val codigo = scan.nextLine()
        var meuJogo: Jogo? = null
        val resultado = runCatching {
            val meuInfoJogo = ConsumoApi().buscaJogo(codigo)
            meuJogo = meuInfoJogo.info.criarJogo()
        }

        resultado.onFailure{
            println("Codigo não encontrado! Tente outro ID!")
        }

        resultado.onSuccess {
            println(meuJogo)
            print("\nDeseja acrescentar um descrição ao jogo?[s/n] ")
            val resposta = scan.nextLine()
            if(resposta.equals("s",true)){
                print("Insira descrição: ")
                val descricao:String = scan.nextLine()
                meuJogo?.descricao = descricao
            }
            else{
                meuJogo?.descricao = meuJogo!!.titulo
            }
            gamer.jogosBuscados.add(meuJogo)
        }
        print("Deseja buscar um novo jogo?[s/n]: ")
        val resposta = scan.nextLine()

    }while(resposta.equals("s",true))
    print("Jogos buscados: ")
    gamer.toStringList()

    println("Jogos ordenados por titulo")
    gamer.jogosBuscados.forEach{
        println("Titulo:  ${it?.titulo}")
    }


    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("batman",true)?: false
    }

    println("Jogos filtrados")
    println(jogosFiltrados)

    println("Deseja escluir algum jogo?[s/n]")
    val op = scan.nextLine()

    if(op.equals("s",true)){
        print("Insira indice do jogo em questão:")
        val idJogo = scan.nextInt()
        val resultado2 = runCatching {
            gamer.jogosBuscados.removeAt(idJogo)
        }
        resultado2.onSuccess {
            println("Jogo excluido com sucesso!")
        }
        resultado2.onFailure {
            println("Não foi possivel excluir esse indice!")
        }
    }
    println("Programa executou com sucesso!")
}


