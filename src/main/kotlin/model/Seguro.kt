package model

abstract class Seguro(private var numPoliza: Int, private var dniTitular: String, protected var importe: Double): IExportable {

    companion object{
        private var polizaCont: Int = 100000
        /**
         *
         */
        fun generarNumPoliza(): Int{
            polizaCont += 1
            return polizaCont
        }
    }

    init {
        numPoliza = generarNumPoliza()
    }

    /**
     *
     */
    fun comprobarNumpoliza(numPoliza: Int): Boolean {
        return this.numPoliza == numPoliza
    }

    /**
     *
     */
    override fun serializar(): String {
        return "$numPoliza;$dniTitular;$importe"
    }

    /**
     *
     */
    override fun toString(): String {
        return "Seguro(numPoliza=$numPoliza, dniTitular=$dniTitular, importe=${String.format("%2.f", importe)}"
    }

    /**
     *
     */
    override fun hashCode(): Int {
        return super.hashCode()
    }

    /**
     *
     */
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}