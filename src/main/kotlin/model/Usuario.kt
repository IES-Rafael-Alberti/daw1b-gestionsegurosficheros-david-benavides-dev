package model

import utils.Seguridad

/**
 *
 */
class Usuario(var nombre: String, clave: String, var perfil: Perfil) : IExportable {
    companion object {
        /**
         *
         */
        fun crearUsuario(datos: List<String>): Usuario {
            require(datos.size == 3) { "*ERROR* No hay suficientes datos para crear un usuario." }
            return Usuario(datos[0], datos[1], Perfil.getPerfil(datos[2]))
        }
    }

    /**
     *
     */
    var clave: String = Seguridad.encriptarClave(clave)
        private set

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
    override fun serializar(separador: String): String {
        return "$nombre$separador$clave$separador$perfil"
    }
}