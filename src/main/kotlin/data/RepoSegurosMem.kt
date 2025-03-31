package data

import model.Seguro

/**
 *
 */
open class RepoSegurosMem : IRepoSeguros {
    open val seguros: MutableList<Seguro> = mutableListOf()

    /**
     *
     */
    override fun agregar(seguro: Seguro): Boolean {
        return seguros.add(seguro)
    }

    /**
     *
     */
    override fun buscar(numPoliza: Int): Seguro? {
        return seguros.find { it.numPoliza == numPoliza }
    }

    /**
     *
     */
    override fun eliminar(seguro: Seguro): Boolean {
        return seguros.remove(seguro)
    }

    /**
     *
     */
    override fun eliminar(numPoliza: Int): Boolean {
        val usuario = buscar(numPoliza) ?: return false
        return eliminar(usuario)
    }

    /**
     *
     */
    override fun obtenerTodos(): List<Seguro> {
        return seguros
    }

    /**
     *
     */
    override fun obtener(tipoSeguro: String): List<Seguro> {
        return seguros.filter { it.tipoSeguro() == tipoSeguro }
    }
}