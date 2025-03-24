package model

class SeguroVida : Seguro {
    private var descripcion: String
    private var combustible: Int
    private var tipoAuto: Auto
    private var cobertura: Cobertura
    private var asistenciaCarretera: Boolean
    private var numPartes: Int

    companion object {
        private var numPolizasAuto: Int = 400000

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
                return SeguroVida()
            } catch (e: IllegalArgumentException) {
                return null
            }
        }

        private fun numPolizasAuto(): Int {
            numPolizasAuto++
            return numPolizasAuto
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
}