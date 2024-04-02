package br.com.alura.alugames.dados

import javax.persistence.*

@Entity // Estrategia single table | As tabelas serão "achatadas"
@Table(name = "planos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TipoPLano",discriminatorType = DiscriminatorType.STRING)

open class PlanoEntity(val tipo: String,
                       @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int = 0) {
    open fun obterId(): Int {
        return id
    }

    override fun toString(): String {
        return "PlanoEntity(tipo='$tipo', id=$id)"
    }

}
@Entity
@DiscriminatorValue("Avulso")
class PlanoAvulsoEntity(id: Int = 0,tipo: String = "Plano Avulso"): PlanoEntity(tipo,id)

@Entity
@DiscriminatorValue("TipoAssinatura" )
class PlanoAssinaturaEntity(
    id: Int = 0,
    tipo: String = "Plano Assinatura",
    val mensalidade: Double = 0.0,
    val jogosIncluidos: Int = 0,
    val percentualDescontoReputacao: Double = 0.0
): PlanoEntity(tipo,id)
