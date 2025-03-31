package data

import model.Seguro
import model.SeguroAuto
import model.SeguroHogar
import model.SeguroVida
import utils.IUtilFicheros

/**
 *
 */
class RepoSegurosFich(private val rutaArchivo: String, private val fich: IUtilFicheros): RepoSegurosMem(), ICargarSegurosIniciales {

    /**
     *
     */
    override fun cargarSeguros(mapa: Map<String, (List<String>) -> Seguro>): Boolean {
        TODO("Not yet implemented")
    }


    init {
        // cargarSeguros()
    }

    /**
     *
     */
    override fun agregar(seguro: Seguro): Boolean {
        return super.agregar(seguro)
    }

    /**
     *
     */
    override fun buscar(numPoliza: Int): Seguro? {
        return super.buscar(numPoliza)
    }

    /**
     *
     */
    override fun eliminar(seguro: Seguro): Boolean {
        return super.eliminar(seguro)
    }

    /**
     *
     */
    override fun eliminar(numPoliza: Int): Boolean {
        return super.eliminar(numPoliza)
    }

    /**
     *
     */
    override fun obtenerTodos(): List<Seguro> {
        return super.obtenerTodos()
    }

    /**
     *
     */
    override fun obtener(tipoSeguro: String): List<Seguro> {
        return super.obtener(tipoSeguro)
    }

    /**
     *
     */
    private fun actualizarContadores(seguros: List<Seguro>) {
        // Actualizar los contadores de polizas del companion object según el tipo de seguro
        val maxHogar = seguros.filter { it.tipoSeguro() == "SeguroHogar" }.maxOfOrNull { it.numPoliza }
        val maxAuto = seguros.filter { it.tipoSeguro() == "SeguroAuto" }.maxOfOrNull { it.numPoliza }
        val maxVida = seguros.filter { it.tipoSeguro() == "SeguroVida" }.maxOfOrNull { it.numPoliza }

        if (maxHogar != null) SeguroHogar.numPolizasHogar = maxHogar
        if (maxAuto != null) SeguroAuto.numPolizasAuto = maxAuto
        if (maxVida != null) SeguroVida.numPolizasVida = maxVida
    }

}