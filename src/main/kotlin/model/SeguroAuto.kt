package model

/**
 * Clase que representa un seguro de automóvil, derivada de la clase [Seguro].
 * Esta clase contiene detalles específicos para un seguro de coche, como la descripción, tipo de combustible,
 * tipo de vehículo, cobertura, asistencia en carretera y número de partes.
 */
class SeguroAuto : Seguro {
    private var descripcion: String
    private var combustible: Int
    private var tipoAuto: Auto
    private var cobertura: Cobertura
    private var asistenciaCarretera: Boolean
    private var numPartes: Int

    companion object {
        internal var numPolizasAuto: Int = 400000

        private const val PORCENTAJE_INCREMENTO_PARTES = 2

        /**
         * Función que crea un objeto [SeguroAuto] a partir de una lista de cadenas de texto.
         * Si los datos son inválidos o incompletos, retorna `null`.
         *
         * @param datos Lista con los datos necesarios para crear el seguro de automóvil.
         * @return Un objeto [SeguroAuto] o `null` si los datos son incorrectos o insuficientes.
         */
        fun crearSeguro(datos: List<String>): SeguroAuto? {
            if (datos.size < 9) {
                return null
            }
            try {
                datos[0].toInt()
                datos[1].isNotBlank()
                datos[2].toDouble()
                datos[3].isNotBlank()
                datos[4].toInt()
                datos[5].isNotBlank()
                datos[6].isNotBlank()
                datos[7].toBoolean()
                datos[8].toInt()
                return SeguroAuto(datos[0].toInt(), datos[1], datos[2].toDouble(), datos[3], datos[4].toInt(), Auto.getAuto(datos[5]), Cobertura.getCobertura(datos[6]), datos[7].toBoolean(), datos[8].toInt())
            } catch (e: IllegalArgumentException) {
                return null
            }
        }
    }

    constructor(dniTitular: String, importe: Double, descripcion: String, combustible: Int, tipoAuto: Auto, cobertura: Cobertura, asistenciaCarretera: Boolean, numPartes: Int) : super(numPoliza = numPolizasAuto++, dniTitular, importe) {
        this.descripcion = descripcion
        this.combustible = combustible
        this.tipoAuto = tipoAuto
        this.cobertura = cobertura
        this.asistenciaCarretera = asistenciaCarretera
        this.numPartes = numPartes
    }

    private constructor(numPoliza: Int, dniTitular: String, importe: Double, descripcion: String, combustible: Int, tipoAuto: Auto, cobertura: Cobertura, asistenciaCarretera: Boolean, numPartes: Int) : super(numPoliza, dniTitular, importe) {
        this.descripcion = descripcion
        this.combustible = combustible
        this.tipoAuto = tipoAuto
        this.cobertura = cobertura
        this.asistenciaCarretera = asistenciaCarretera
        this.numPartes = numPartes
    }

    /**
     * Calcula el importe del seguro para el año siguiente aplicando un interés adicional
     * basado en el interés proporcionado y el número de partes del seguro.
     *
     * @param interes El porcentaje de interés aplicable al seguro.
     * @return El importe calculado para el año siguiente, teniendo en cuenta el incremento
     * por el número de partes registrados.
     */
    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        val incrementoPartes = numPartes * PORCENTAJE_INCREMENTO_PARTES

        // Importe del año siguiente basándose en el interés que se pasa por parámetro, sumándole un interés residual del 2%(PORCENTAJE_INCREMENTO_PARTES) por cada parte declarado.
        return importe * (1 + (interes + incrementoPartes) / 100)
    }

    /**
     * Serializa el objeto [SeguroAuto] en una cadena de texto con los datos separados por el
     * separador proporcionado.
     *
     * @param separador El carácter o cadena que se utilizará para separar los datos.
     * @return Una cadena con todos los datos del seguro de automóvil serializados.
     */
    override fun serializar(separador: String): String {
        return super.serializar(separador) + "$separador$descripcion$separador$combustible$separador$tipoAuto$separador$cobertura$separador$asistenciaCarretera$separador$numPartes"
    }

    /**
     * Devuelve una representación en cadena del objeto [SeguroAuto] con todos sus datos.
     *
     * @return Una cadena con la descripción completa del seguro de automóvil.
     */
    override fun toString(): String {
        return "Seguro Auto=(${obtenerDatosSeguro()}, descripcion=$descripcion, combustible=$combustible, tipoAuto=$tipoAuto, cobertura=$cobertura, asistenciaCarretera=$asistenciaCarretera, numPartes=$numPartes"
    }
}