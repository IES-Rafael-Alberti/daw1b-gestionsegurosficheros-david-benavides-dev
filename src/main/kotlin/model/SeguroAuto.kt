package model

/**
 *
 */
class SeguroAuto : Seguro {
    private var descripcion: String
    private var combustible: Int
    private var tipoAuto: Auto
    private var cobertura: Cobertura
    private var asistenciaCarretera: Boolean
    private var numPartes: Int

    companion object {
        private var numPolizasAuto: Int = 400000

        private const val PORCENTAJE_INCREMENTO_PARTES = 2

        /**
         *
         */
        fun crearSeguro(datos: List<String>): SeguroAuto? {
            if (datos.size < 9) {
                return null
            }
            try {
                datos[0].toInt()
                datos[1].isNotEmpty()
                datos[2].toDouble()
                datos[3].isNotEmpty()
                datos[4].toInt()
                // datos[5].uppercase() in Auto.entries.map{ it.name }
                datos[5].isNotEmpty()
                datos[6].isNotEmpty()
                datos[7].toBoolean()
                datos[8].toInt()
                return SeguroAuto(datos[0].toInt(), datos[1], datos[2].toDouble(), datos[3], datos[4].toInt(), Auto.getAuto(datos[5]), Cobertura.getCobertura(datos[6]), datos[7].toBoolean(), datos[8].toInt())
            } catch (e: IllegalArgumentException) {
                return null
            }
        }
    }

    /**
     *
     */
    constructor(dniTitular: String, importe: Double, descripcion: String, combustible: Int, tipoAuto: Auto, cobertura: Cobertura, asistenciaCarretera: Boolean, numPartes: Int) : super(numPoliza = numPolizasAuto++, dniTitular, importe) {
        this.descripcion = descripcion
        this.combustible = combustible
        this.tipoAuto = tipoAuto
        this.cobertura = cobertura
        this.asistenciaCarretera = asistenciaCarretera
        this.numPartes = numPartes
    }

    /**
     *
     */
    private constructor(numPoliza: Int, dniTitular: String, importe: Double, descripcion: String, combustible: Int, tipoAuto: Auto, cobertura: Cobertura, asistenciaCarretera: Boolean, numPartes: Int) : super(numPoliza, dniTitular, importe) {
        this.descripcion = descripcion
        this.combustible = combustible
        this.tipoAuto = tipoAuto
        this.cobertura = cobertura
        this.asistenciaCarretera = asistenciaCarretera
        this.numPartes = numPartes
    }

    /**
     *
     */
    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        val incrementoPartes = numPartes * PORCENTAJE_INCREMENTO_PARTES

        // Importe del año siguiente basándose en el interés que se pasa por parámetro, sumándole un interés residual del 2%(PORCENTAJE_INCREMENTO_PARTES) por cada parte declarado.
        return importe * (1 + (interes + incrementoPartes) / 100)
    }

    /**
     * Retorna el nombre de la clase usando this::class.simpleName y el operador elvis para indicar al compilador que si simpleName es null, entonces retorna el valor "Desconocido".
     */
    override fun tipoSeguro(): String {
        return this::class.simpleName?: "Desconocido"
    }

    /**
     *
     */
    override fun serializar(separador: String): String {
        return super.serializar(separador) + "$separador$descripcion$separador$combustible$separador$tipoAuto$separador$cobertura$separador$asistenciaCarretera$separador$numPartes"
    }

    /**
     *
     */
    override fun toString(): String {
        return "Seguro Auto=(${obtenerDatosSeguro()}, descripcion=$descripcion, combustible=$combustible, tipoAuto=$tipoAuto, cobertura=$cobertura, asistenciaCarretera=$asistenciaCarretera, numPartes=$numPartes"
    }
}