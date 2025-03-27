package data

import model.Perfil
import model.Usuario
import utils.IUtilFicheros

/**
 *
 */
class RepoUsuariosFich(private val rutaArchivo: String, private val fich: IUtilFicheros) : RepoUsuariosMem(), ICargarUsuariosIniciales {
    private val usuarios: MutableList<Usuario> = mutableListOf()

    init {
        cargarUsuarios()
    }

    /**
     *
     */
    override fun cargarUsuarios(): Boolean {
        if (fich.existeFichero(rutaArchivo)) {

            val listaStrings = fich.leerArchivo(rutaArchivo)

            if (usuarios.isNotEmpty()) {
                usuarios.clear()
            }

            for (linea in listaStrings) {
                val datos = linea.split(";")
                try {
                    require(datos.size == 3)
                } catch (e: IllegalArgumentException) {
                    return false
                }
                usuarios.add(Usuario(datos[0], datos[1], Perfil.getPerfil(datos[2])))
            }
            return true
        }
        return false
    }

    /**
     *
     */
    override fun agregar(usuario: Usuario): Boolean {
        if (fich.escribirArchivo(rutaArchivo, usuarios.filter { it != usuario })) {
            return super.agregar(usuario)
        }
        return false
    }

    override fun buscar(nombreUsuario: String): Usuario? {
        return super.buscar(nombreUsuario)
    }

    override fun eliminar(usuario: Usuario): Boolean {
        if (fich.escribirArchivo(rutaArchivo, usuarios.filter { it != usuario })) {
            return super.eliminar(usuario)
        }
        return false
    }

    override fun eliminar(nombreUsuario: String): Boolean {
        return super.eliminar(nombreUsuario)
    }

    override fun obtenerTodos(): List<Usuario> {
        return super.obtenerTodos()
    }

    override fun obtener(perfil: Perfil): List<Usuario> {
        return super.obtener(perfil)
    }

    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        return super.cambiarClave(usuario, nuevaClave)
    }
}