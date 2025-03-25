package data

import model.Perfil
import model.Usuario

sealed class RepoUsuariosMem : IRepoUsuarios {
    private val dbMemoria: MutableList<Usuario> = mutableListOf()

    /**
     *
     */
    override fun agregar(usuario: Usuario): Boolean {
        if (buscar(usuario.nombre) != null) {
            dbMemoria.add(usuario)
            return true
        }
        return false
    }

    /**
     *
     */
    override fun buscar(nombreUsuario: String): Usuario? {
        return dbMemoria.find { it.nombre == nombreUsuario }
    }

    /**
     *
     */
    override fun eliminar(usuario: Usuario): Boolean {
        if (buscar(usuario.nombre) != null) {
            dbMemoria.remove(usuario)
            return true
        }
        return false
    }

    /**
     *
     */
    override fun eliminar(nombreUsuario: String): Boolean {
        val usuarioBorrar = buscar(nombreUsuario)

        if (usuarioBorrar != null) {
            eliminar(usuarioBorrar)
            return true
        }
        return false
    }

    /**
     *
     */
    override fun obtenerTodos(): List<Usuario> {
        return dbMemoria
    }

    /**
     *
     */
    override fun obtener(perfil: Perfil): List<Usuario> {
        return dbMemoria.filter { it.perfil == perfil }
    }

    /**
     *
     */
    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        if (buscar(usuario.nombre) != null) {
            usuario.cambiarClave(nuevaClave)
            return true
        }
        return false
    }
}