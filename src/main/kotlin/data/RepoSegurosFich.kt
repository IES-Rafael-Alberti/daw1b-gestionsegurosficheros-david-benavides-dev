package data

import model.Seguro
import model.SeguroAuto
import model.SeguroHogar
import model.SeguroVida
import utils.Ficheros
import utils.IUtilFicheros

/**
 *
 */
class RepoSegurosFich(val rutaArchivo: String, val fichero: Ficheros) : RepoSegurosMem(), ICargarSegurosIniciales {
    override fun cargarSeguros(mapa: Map<String, (List<String>) -> Seguro?>): Boolean {
        val listaStrings = fichero.leerArchivo(rutaArchivo)
        for (elemento in listaStrings) {
            val numPoliza = elemento.split(";").first()
            if (numPoliza >= 100000.toString() && numPoliza < 400000.toString()) {
                val funcion = mapa["SeguroHogar"]

                if (funcion != null) {
                    val seguro = funcion(elemento.split(";"))
                    if(seguro?.let { super.agregar(it) } == true){
                        actualizarContadores(super.seguros)
                        return true
                    }
                } else {
                    println("ERROR.")
                }
            }
            if (numPoliza >= 400000.toString() && numPoliza < 800000.toString()) {
                val funcion = mapa["SeguroAuto"]

                if (funcion != null) {
                    val seguro = funcion(elemento.split(";"))
                    if(seguro?.let { super.agregar(it) } == true){
                        actualizarContadores(super.seguros)
                        return true
                    }
                } else {
                    println("ERROR.")
                }
            }
            if (numPoliza >= 800000.toString()) {
                val funcion = mapa["SeguroVida"]

                if (funcion != null) {
                    val seguro = funcion(elemento.split(";"))
                    if(seguro?.let { super.agregar(it) } == true){
                        actualizarContadores(super.seguros)
                        return true
                    }
                } else {
                    println("ERROR.")
                }
            }
        }
        return false
    }

    override fun agregar(seguro: Seguro): Boolean {
        return if (fichero.agregarLinea(rutaArchivo, seguro.serializar())) {
            super.agregar(seguro)
        } else {
            false
        }
    }

    override fun eliminar(seguro: Seguro): Boolean {
        val lista = super.seguros.toMutableList()
        lista.remove(seguro)

        return if (fichero.escribirArchivo(rutaArchivo, lista)) {
            super.eliminar(seguro)
        } else {
            false
        }
    }

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