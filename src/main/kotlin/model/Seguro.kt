package model

abstract class Seguro(private var numPoliza: Int, private var dniTitular: String, protected var importe: Double): IExportable {
    /**
     *
     */
    fun comprobarNumpoliza(numPoliza: Int): Boolean {
        return this.numPoliza == numPoliza
    }

    init {
        require(dniTitular.matches(Regex("^[0-9]{8}[A-Z]$"))) { "*ERROR* DNI incorrecto." }
    }

    /**
     * Métodos abstractos
     */
    abstract fun calcularImporteAnioSiguiente(interes: Double): Double
    abstract fun tipoSeguro(): String

    /**
     *
     */
    override fun serializar(separador: String): String {
        return "$numPoliza$separador$dniTitular$separador$importe"
    }

    /**
     *
     */
    protected fun obtenerDatosSeguro(): String {
        return "numPoliza=$numPoliza, dniTitular=$dniTitular, importe=${"%.2f".format(importe)}"
    }

    /**
     *
     */
    override fun toString(): String {
        return "Seguro(${obtenerDatosSeguro()})"
    }

    /**
     *
     */
    override fun hashCode(): Int {
        return numPoliza.hashCode() + dniTitular.hashCode()
    }

    /**
     *
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Seguro) return false
        return numPoliza == other.numPoliza && dniTitular == other.dniTitular
    }
}