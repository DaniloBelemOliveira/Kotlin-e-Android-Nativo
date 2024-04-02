package org.example.br.com.alura.modelo

import com.google.gson.annotations.Expose
import javax.persistence.*

@Entity
@Table(name = "jogos")
data class Jogo(@Expose var titulo: String,
                @Expose var capa: String,
                @Expose var descricao: String,
                @Expose var preco: Double
                ) {

    constructor(titulo: String,capa: String,descricao: String,preco: Double,id:Int):
            this(titulo,capa,descricao,preco){
        this.id = id
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0

    @ElementCollection
    @Column(name = "avaliacao")
    private val avaliações:MutableList<Int> = mutableListOf<Int>()
    var nota:Double = 0.0
    fun recomendar(nota:Int){
        avaliações.add(nota)
        this.nota = avaliações.average()
    }

    override fun toString(): String {
        return "Jogo(" +
                "\ntitulo='$titulo'," +
                "\ncapa='$capa'," +
                "\npreço='${preco}'"+
                "\ndescricao='$descricao'" +
                "\nnota='$nota'" +
                "\nid='$id'" +
                "\n)"
    }
}
