package data

import model.Usuario
import utils.IUtilFicheros

/**
 *
 */
class RepoUsuariosFich(private val rutaArchivo: String, private val fich: IUtilFicheros) : RepoUsuariosMem(), ICargarUsuariosIniciales {
    override val usuarios: MutableList<Usuario> = mutableListOf()

    init {
        cargarUsuarios()
    }

    /**
     *
     */
    override fun cargarUsuarios(): Boolean {
        if (fich.existeFichero(rutaArchivo)) {

            val listaStrings = fich.leerArchivo(rutaArchivo)

            for (linea in listaStrings) {
                val datos = linea.split(";")
                try {
                    require(datos.size == 3)
                } catch (e: IllegalArgumentException) {
                    return false
                }
                usuarios.add(Usuario.crearUsuario(datos))
            }
            return true
        }
        return false
    }

    /**
     *
     */
    override fun agregar(usuario: Usuario): Boolean {
        if (super.agregar(usuario)) {
            fich.agregarLinea(rutaArchivo, usuario.serializar())
        }
        return false
    }

    /**
     *
     */
    override fun eliminar(usuario: Usuario): Boolean {
        if (fich.escribirArchivo(rutaArchivo, usuarios.filter { it != usuario })) {
            return super.eliminar(usuario)
        }
        return false
    }

    /**
     *
     */
    override fun eliminar(nombreUsuario: String): Boolean {
        if (fich.escribirArchivo(rutaArchivo, usuarios.filter { it.nombre != nombreUsuario })) {
            return super.eliminar(nombreUsuario)
        }
        return false
    }

    /**
     *
     */
    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        if (super.cambiarClave(usuario, nuevaClave)) {
            fich.escribirArchivo(rutaArchivo, usuarios)
            return true
        }
        return false
    }
}