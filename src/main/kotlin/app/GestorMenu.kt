package app

import model.Perfil
import service.IServSeguros
import service.IServUsuarios
import ui.IEntradaSalida

/**
 *
 */
class GestorMenu(val nombreUsuario: String, val perfilUsuario: Perfil, val consola: IEntradaSalida, val gestionUsuarios: IServUsuarios, val gestionSeguros: IServSeguros) {
    companion object {
        // TODO Strings de los diferentes tipos de menu maybe?
    }

    /**
     *
     */
    fun nuevoUsuario() {
        if (consola.preguntar("¿Desea añadir un nuevo usuario? s/n >> ")) {
            try {
                val nombre = consola.pedirInfo("Introduce el nombre del usuario >> ")
                val pwd = consola.pedirInfoOculta("Introduce su contraseña >> ")
                val perfil = consola.pedirInfo("Perfil de usuario >> ")
                gestionUsuarios.agregarUsuario(nombre, pwd, Perfil.getPerfil(perfil))
            } catch (e: IllegalArgumentException) {
                consola.mostrarError(e.message.toString())
            }
        }
    }

    /**
     *
     */
    fun eliminarUsuario() {
        if (consola.preguntar("¿Desea eliminar un usuario existente? s/n >> ")) {
            try {
                if (!gestionUsuarios.eliminarUsuario(consola.pedirInfo("Introduzca el nombre del usuario a eliminar >> "))) {
                    throw IllegalArgumentException("Usuario no encontrado.")
                } else {
                    consola.mostrar("Usuario eliminado con éxito.", pausa = true)
                }
            } catch (e: IllegalArgumentException) {
                consola.mostrarError(e.message.toString())
            }
        }
    }

    /**
     *
     */
    fun cambiarClaveUsuario() {
        val usuario = gestionUsuarios.buscarUsuario(consola.pedirInfo("Introduzca el nombre del usuario >> "))
        if (usuario != null) {
            val pwd = consola.pedirInfo("Introduzca la nueva contraseña para el usuario ${usuario.nombre} >> ")
            gestionUsuarios.cambiarClave(usuario, pwd)
        } else {
            consola.mostrarError("Usuario no encontrado.")
        }
    }

    /**
     *
     */
    fun consultarUsuarios() {
        if (consola.preguntar("¿Desea consultar la lista de usuarios? s/n >> ")) {
            for (usuario in gestionUsuarios.consultarTodos()) {
                consola.mostrar(usuario.toString(), true)
            }
        }
    }

    /**
     *
     */
    fun contratarSeguro() {

    }

    fun eliminarSeguro() {

    }

}