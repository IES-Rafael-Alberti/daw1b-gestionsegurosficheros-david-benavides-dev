package utils

import model.IExportable
import model.Seguro
import ui.IEntradaSalida
import java.io.File

class Ficheros(val entradaSalida: IEntradaSalida) : IUtilFicheros {
    override fun leerArchivo(ruta: String): List<String> {
        if (!existeFichero(ruta)) {
            throw IllegalArgumentException("*ERROR* El archivo no existe.")
        }
        val rutaArchivo = File(ruta)
        return rutaArchivo.readLines()
    }

    override fun agregarLinea(ruta: String, linea: String): Boolean {
        if (!existeFichero(ruta)) {
            return false
        } else {
            File(ruta).appendText(linea)
            return true
        }
    }

    override fun <T : IExportable> escribirArchivo(ruta: String, elementos: List<T>): Boolean {
        TODO("Not yet implemented")
    }

    override fun existeFichero(ruta: String): Boolean {
        return File(ruta).exists()
    }

    override fun existeDirectorio(ruta: String): Boolean {
        return File(ruta).isDirectory
    }
}