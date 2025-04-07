package model

/**
 *
 */
class SeguroHogar : Seguro {
    private var metrosCuadrados: Int
    private var valorContenido: Double
    private var direccion: String
    private var anioConstruccion: Int

    companion object {
        const val PORCENTAJE_INCREMENTO_ANIOS = 0.02
        const val CICLO_ANIOS_INCREMENTO = 5

        // Constante con el año para evitar ediciones de fecha local del ordenador al usar time.year.now
        const val ANIO_ACTUAL = 2025

        internal var numPolizasHogar: Int = 100000

        /**
         * Crea un seguro utilizando el constructor privado
         */
        fun crearSeguro(datos: List<String>): SeguroHogar? {
            if (datos.size < 7) {
                return null
            }
            try {
                datos[0].toInt()
                datos[1].isNotBlank()
                datos[2].toDouble()
                datos[3].toInt()
                datos[4].toDouble()
                datos[5].isNotBlank()
                datos[6].toInt()
                return SeguroHogar(datos[0].toInt(), datos[1], datos[2].toDouble(), datos[3].toInt(), datos[4].toDouble(), datos[5], datos[6].toInt())
            } catch (e: IllegalArgumentException) {
                return null
            }
        }
    }

    /**
     * Constructor para contratar NUEVO seguro con número de póliza automático.
     */
    constructor(dniTitular: String, importe: Double, metrosCuadrados: Int, valorContenido: Double, direccion: String, anioConstruccion: Int) : super(numPoliza = numPolizasHogar++, dniTitular, importe) {
        this.metrosCuadrados = metrosCuadrados
        this.valorContenido = valorContenido
        this.direccion = direccion
        this.anioConstruccion = anioConstruccion
    }

    /**
     * Constructor para crear una poliza desde datos persistentes.
     */
    private constructor(numPoliza: Int, dniTitular: String, importe: Double, metrosCuadrados: Int, valorContenido: Double, direccion: String, anioConstruccion: Int) : super(numPoliza, dniTitular, importe) {
        this.metrosCuadrados = metrosCuadrados
        this.valorContenido = valorContenido
        this.direccion = direccion
        this.anioConstruccion = anioConstruccion
    }


    /**
     *
     */
    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        val antiguedad = ANIO_ACTUAL - anioConstruccion

        val aniosCompletos = antiguedad / CICLO_ANIOS_INCREMENTO
        val interesResidual = aniosCompletos * PORCENTAJE_INCREMENTO_ANIOS

        val interesTotal = interes + interesResidual * 100

        return importe + (importe * interesTotal / 100)
    }

    /**
     * SUPER:"$numPoliza;$dniTitular;$importe"
     */
    override fun serializar(separador: String): String {
        return super.serializar(separador) + "$separador$metrosCuadrados$separador$valorContenido$separador$direccion$separador$anioConstruccion"
    }

    override fun toString(): String {
        return "Seguro Hogar=(${obtenerDatosSeguro()}, metrosCuadrados=$metrosCuadrados, valorContenido=$valorContenido, direccion=$direccion, anioConstruccion=$anioConstruccion)"
    }
}