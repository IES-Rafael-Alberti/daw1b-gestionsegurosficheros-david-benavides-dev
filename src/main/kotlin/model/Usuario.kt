package model

import utils.Seguridad

/**
 *
 */
class Usuario(var nombre: String, var clave: String, var perfil: String): IExportable {
    companion object{
        /**
         *
         */
        fun crearUsuario(datos: List<String>): Usuario {
            try {
                Usuario(datos[0], datos[1], datos[2])
            } catch (e: IllegalArgumentException) {
                println("ERROR FATAL")
            }
            return Usuario(datos[0], datos[1], datos[2])
        }
    }

    /**
     *
     */
    init {
        clave = Seguridad.encriptarClave(clave)
    }

    /**
     *
     */
    fun verificarClave(claveEncriptada: String): Boolean {
        return Seguridad.verificarClave(claveEncriptada, clave)
    }

    /**
     *
     */
    fun cambiarClave(nuevaClaveEncriptada: String) {
        this.clave = nuevaClaveEncriptada
    }

    /**
     *
     */
    override fun serializar(): String {
        return "$nombre;$clave;$perfil"
    }
}