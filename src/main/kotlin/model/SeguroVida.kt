package model

import java.time.LocalDate

/**
 *
 */
class SeguroVida : Seguro {
    private var fechaNac: LocalDate
    private var nivelRiesgo: Riesgo
    private var indemnizacion: Double

    companion object {
        private var numPolizasVida: Int = 800000

        /**
         *
         */
        fun crearSeguro(datos: List<String>): SeguroVida? {
            if (datos.size < 6) {
                return null
            }
            try {
                datos[0].toInt()
                datos[1].isNotBlank()
                datos[2].toDouble()
                LocalDate.parse(datos[3])
                datos[4].isNotBlank()
                datos[5].toDouble()
                return SeguroVida(datos[0].toInt(), datos[1], datos[2].toDouble(), LocalDate.parse(datos[3]), Riesgo.getRiesgo(datos[4]), datos[5].toDouble())
            } catch (e: IllegalArgumentException) {
                return null
            }
        }
    }

    /**
     *
     */
    constructor(dniTitular: String, importe: Double, fechaNac: LocalDate, nivelRiesgo: Riesgo, indemnizacion: Double) : super(numPoliza = numPolizasVida++, dniTitular, importe) {
        this.fechaNac = fechaNac
        this.nivelRiesgo = nivelRiesgo
        this.indemnizacion = indemnizacion

    }

    /**
     *
     */
    private constructor(numPoliza: Int, dniTitular: String, importe: Double, fechaNac: LocalDate, nivelRiesgo: Riesgo, indemnizacion: Double) : super(numPoliza, dniTitular, importe) {
        this.fechaNac = fechaNac
        this.nivelRiesgo = nivelRiesgo
        this.indemnizacion = indemnizacion

    }

    /**
     *
     */
    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        TODO("Not yet implemented")
    }

    /**
     *
     */
    override fun tipoSeguro(): String {
        return this::class.simpleName ?: "Desconocido"
    }

    /**
     *
     */
    override fun serializar(separador: String): String {
        return super.serializar(separador) + "$separador$fechaNac$separador$nivelRiesgo$separador$indemnizacion"
    }

    /**
     *
     */
    override fun toString(): String {
        return "Seguro Vida=(${obtenerDatosSeguro()}, fechaNac=$fechaNac, nivelRiesgo=$nivelRiesgo, indemnizacion=$indemnizacion)"
    }
}