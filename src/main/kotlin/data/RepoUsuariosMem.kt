package data

import model.Perfil
import model.Usuario
import utils.Seguridad

/**
 * Implementa un repositorio en memoria para gestionar usuarios.
 * Esta clase proporciona funcionalidades para agregar, buscar, eliminar y obtener usuarios.
 * Además, permite cambiar la clave de un usuario de manera segura.
 */
open class RepoUsuariosMem : IRepoUsuarios {
    open val usuarios: MutableList<Usuario> = mutableListOf()

    /**
     * Agrega un usuario al repositorio si no existe previamente un usuario con el mismo nombre.
     *
     * @param usuario El objeto [Usuario] a agregar al repositorio.
     * @return `true` si el usuario fue agregado exitosamente, `false` si el usuario ya existe.
     */
    override fun agregar(usuario: Usuario): Boolean {
        if (buscar(usuario.nombre) == null) {
            usuarios.add(usuario)
            return true
        }
        return false
    }

    /**
     * Busca un usuario en el repositorio por su nombre de usuario.
     *
     * @param nombreUsuario El nombre del usuario que se desea buscar.
     * @return El objeto [Usuario] correspondiente, o `null` si no se encuentra el usuario.
     */
    override fun buscar(nombreUsuario: String): Usuario? {
        return usuarios.find { it.nombre == nombreUsuario }
    }

    /**
     * Elimina un usuario del repositorio.
     *
     * @param usuario El objeto [Usuario] a eliminar.
     * @return `true` si el usuario fue eliminado exitosamente, `false` si no se encuentra el usuario.
     */
    override fun eliminar(usuario: Usuario): Boolean {
        if (buscar(usuario.nombre) != null) {
            usuarios.remove(usuario)
            return true
        }
        return false
    }

    /**
     * Elimina un usuario del repositorio utilizando su nombre de usuario.
     *
     * @param nombreUsuario El nombre del usuario a eliminar.
     * @return `true` si el usuario fue eliminado exitosamente, `false` si no se encuentra el usuario.
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
     * Obtiene todos los usuarios almacenados en el repositorio.
     *
     * @return Una lista de todos los objetos [Usuario] almacenados.
     */
    override fun obtenerTodos(): List<Usuario> {
        return usuarios
    }

    /**
     * Obtiene todos los usuarios con el perfil específico.
     *
     * @param perfil El perfil de los usuarios que se desean obtener.
     * @return Una lista de objetos [Usuario] que tienen el perfil indicado.
     */
    override fun obtener(perfil: Perfil): List<Usuario> {
        return usuarios.filter { it.perfil == perfil }
    }

    /**
     * Cambia la clave de un usuario y la cifra de manera segura utilizando [Seguridad.encriptarClave].
     *
     * @param usuario El objeto [Usuario] cuya clave será modificada.
     * @param nuevaClave La nueva clave para el usuario.
     * @return `true` si la clave fue cambiada exitosamente, `false` si no se encuentra el usuario.
     */
    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        if (buscar(usuario.nombre) != null) {
            usuario.cambiarClave(Seguridad.encriptarClave(nuevaClave))
            return true
        }
        return false
    }
}