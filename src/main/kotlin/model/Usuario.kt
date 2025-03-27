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
            require(datos[0].isNotBlank()) { "*ERROR* No has introducido un nombre." }
            require(datos[1].isNotBlank()) { "*ERROR* Error con la clave." }
            require(datos[2].isNotBlank()) { "ERROR Error con el perfil." }
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