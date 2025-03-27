package data

import model.Perfil
import model.Usuario

sealed class RepoUsuariosMem : IRepoUsuarios {
    private val usuarios: MutableList<Usuario> = mutableListOf()

    /**
     *
     */
    override fun agregar(usuario: Usuario): Boolean {
        if (buscar(usuario.nombre) != null) {
            usuarios.add(usuario)
            return true
        }
        return false
    }

    /**
     *
     */
    override fun buscar(nombreUsuario: String): Usuario? {
        return usuarios.find { it.nombre == nombreUsuario }
    }

    /**
     *
     */
    override fun eliminar(usuario: Usuario): Boolean {
        if (buscar(usuario.nombre) != null) {
            usuarios.remove(usuario)
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
        return usuarios
    }

    /**
     *
     */
    override fun obtener(perfil: Perfil): List<Usuario> {
        return usuarios.filter { it.perfil == perfil }
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