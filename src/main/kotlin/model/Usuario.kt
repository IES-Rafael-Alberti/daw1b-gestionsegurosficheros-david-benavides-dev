package model

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
        TODO("Nombre único?")
    }

    /**
     *
     */
    fun verificarClave(claveEncriptada: String): Boolean {
        return clave == claveEncriptada
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
        return "nombre;clave;perfil"
    }
}