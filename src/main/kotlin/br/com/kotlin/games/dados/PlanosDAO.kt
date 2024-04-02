package br.com.alura.alugames.dados

import br.com.alura.alugames.modelo.Plano
import javax.persistence.EntityManager
import br.com.alura.alugames.modelo.PlanoAssinatura
import br.com.alura.alugames.modelo.PlanoAvulso

class PlanosDAO(manager:EntityManager):DAO<Plano,PlanoEntity>(manager,PlanoEntity::class.java) {
    override fun toEntity(plano: Plano): PlanoEntity {
        return if (plano is PlanoAssinatura){
            PlanoAssinaturaEntity(
                plano.id,
                plano.tipo,
                plano.mensalidade,
                plano.jogosIncluido,
                plano.percentualAPagar)
        }else{
            PlanoAvulsoEntity(plano.id,plano.tipo)
        }
    }

    override fun toModel(entity: PlanoEntity): Plano {
        return if(entity is PlanoAssinaturaEntity){
            PlanoAssinatura(
                entity.id,
                entity.tipo,
                entity.mensalidade,
                entity.jogosIncluidos,
                entity.percentualDescontoReputacao)
        }
        else {
            PlanoAvulso(entity.id,entity.tipo)
        }
    }
}