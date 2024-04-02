package br.com.alura.alugames.modelo
import org.example.br.com.alura.modelo.Jogo
import java.util.Scanner
import kotlin.random.Random

data class Gamer(val nome:String, var email:String):Recomendado{

    override val media: Double
        get() = listaNotas.average()

    override fun recomendar(nota: Int) {
        listaNotas.add(nota)
    }

    init {
        if(nome.isNullOrBlank()){
            throw IllegalArgumentException("Nome está em branco")
        }
        this.email = validarEmail()
    }

    var dataNascimento:String? = null

    var nomeUsuario:String? = null
        set(value){
            field = value
            if(idInterno.isNullOrBlank()){
                criarIdInterno()
            }
        }

    var idInterno:String? = null
        private set

    var plano:Plano = PlanoAvulso(0,"Avulso")
    var jogosBuscados:MutableList<Jogo?> = mutableListOf<Jogo?>()
    val jogosAlugados:MutableList<AluguelJogo> = mutableListOf<AluguelJogo>()
    private val listaNotas:MutableList<Int> = mutableListOf<Int>()
    val jogosRecomendadedos:MutableList<Jogo> = mutableListOf<Jogo>()

    constructor(nome:String,email:String,dataNascimento:String, nomeUsuario:String ) : this(nome, email){
        this.dataNascimento = dataNascimento
        this.nomeUsuario= nomeUsuario
        criarIdInterno()
    }

    override fun toString(): String {
        return buildString {
            append("Gamer(")
            append("\nnome='$nome', ")
            append("\nemail='$email', ")
            append("\ndataNascimento=$dataNascimento, ")
            append("\nnomeUsuario=$nomeUsuario, ")
            append("\nIdInterno=$idInterno ")
            append("\nReputação=$media ")
        }
    }

    fun toStringList() {
        jogosBuscados.forEach {
            println(it?.titulo)
        }
    }

    fun criarIdInterno(){
        val numero = Random.nextInt(10000)
        val tag = String.format("%05d",numero)
        this.idInterno = this.nome+"#"+tag
    }

    fun validarEmail(): String{
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if(regex.matches(email)){
            return email
        }else{
            throw IllegalArgumentException("Email invalído!")
        }
    }

    fun alugaJogo(jogo:Jogo,periodo:Periodo){
        val aluguelJogo = AluguelJogo(this,jogo, periodo)
        jogosAlugados.add(aluguelJogo)
    }

    fun recomendarJogo(jogo:Jogo,nota:Int){
        jogo.recomendar(nota)
        jogosRecomendadedos.add(jogo)
    }

    fun jogosDoMes(mes:Int):Int{
        val lista = jogosAlugados.filter { aluguel -> aluguel.periodo.dataInicial.monthValue == mes}
            .map {  aluguel -> aluguel.jogo }
        return lista.size
    }

    companion object{
        fun criarGamer(scan: Scanner):Gamer{
            print("\nBoas vindas! Crie um usuario!")
            print("\nInsira nome do usuário: ")
            val nome = scan.nextLine()
            print("Insira email do usuário: ")
            val email = scan.nextLine()
            println("Deseja criar usuario com informções completas?[s/n]")
            val opcao = scan.nextLine()

            if(opcao.equals("s",true)){
                println("Insira data de nascimento(DD/MM/YYYY): ")
                val dataNascimento = scan.nextLine()
                print("Insira nome de usuário: ")
                val nomeUsuario= scan.nextLine()
                return Gamer(nome,email,dataNascimento,nomeUsuario)
            }
            else return  Gamer(nome,email)
        }
    }

}
