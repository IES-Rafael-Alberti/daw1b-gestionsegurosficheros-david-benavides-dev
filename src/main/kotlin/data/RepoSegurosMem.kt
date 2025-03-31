package data

import model.Seguro

/**
 * Representa un repositorio en memoria para gestionar objetos de tipo [Seguro].
 * Esta clase implementa la interfaz [IRepoSeguros] y proporciona métodos para agregar,
 * buscar, eliminar y obtener seguros almacenados en memoria.
 */
open class RepoSegurosMem : IRepoSeguros {
    open val seguros: MutableList<Seguro> = mutableListOf()

    /**
     * Agrega un seguro al repositorio.
     *
     * @param seguro El objeto [Seguro] a agregar al repositorio.
     * @return `true` si el seguro fue agregado exitosamente, `false` si no lo fue.
     */
    override fun agregar(seguro: Seguro): Boolean {
        return seguros.add(seguro)
    }

    /**
     * Busca un seguro por su número de póliza.
     *
     * @param numPoliza El número de póliza del seguro a buscar.
     * @return El objeto [Seguro] encontrado, o `null` si no se encuentra ningún seguro con ese número de póliza.
     */
    override fun buscar(numPoliza: Int): Seguro? {
        return seguros.find { it.numPoliza == numPoliza }
    }

    /**
     * Elimina un seguro del repositorio.
     *
     * @param seguro El objeto [Seguro] que se desea eliminar del repositorio.
     * @return `true` si el seguro fue eliminado exitosamente, `false` si no se encontraba el seguro.
     */
    override fun eliminar(seguro: Seguro): Boolean {
        return seguros.remove(seguro)
    }

    /**
     * Elimina un seguro del repositorio utilizando su número de póliza.
     *
     * @param numPoliza El número de póliza del seguro que se desea eliminar.
     * @return `true` si el seguro fue eliminado exitosamente, `false` si no se encontraba el seguro.
     */
    override fun eliminar(numPoliza: Int): Boolean {
        val usuario = buscar(numPoliza) ?: return false
        return eliminar(usuario)
    }

    /**
     * Obtiene todos los seguros almacenados en el repositorio.
     *
     * @return Una lista de objetos [Seguro] almacenados en el repositorio.
     */
    override fun obtenerTodos(): List<Seguro> {
        return seguros
    }

    /**
     * Obtiene todos los seguros de un tipo específico almacenados en el repositorio.
     *
     * @param tipoSeguro El tipo de seguro que se desea obtener.
     * @return Una lista de objetos [Seguro] que corresponden al tipo de seguro indicado.
     */
    override fun obtener(tipoSeguro: String): List<Seguro> {
        return seguros.filter { it.tipoSeguro() == tipoSeguro }
    }
}