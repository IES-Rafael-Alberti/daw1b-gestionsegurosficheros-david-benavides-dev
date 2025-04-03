package data

import model.Usuario
import utils.IUtilFicheros

/**
 * Implementa un repositorio de usuarios que interactúa con un archivo de almacenamiento.
 * Esta clase extiende [RepoUsuariosMem] y también implementa la interfaz [ICargarUsuariosIniciales].
 * Los usuarios se cargan desde un archivo al inicializar la clase y se realizan operaciones
 * de agregar, eliminar y modificar usuarios, persistiendo los cambios en el archivo correspondiente.
 *
 * @property rutaArchivo Ruta del archivo donde se almacenan los usuarios.
 * @property fich Componente que maneja las operaciones de archivo, implementado a través de [IUtilFicheros].
 */
class RepoUsuariosFich(private val rutaArchivo: String, private val fich: IUtilFicheros) : RepoUsuariosMem(), ICargarUsuariosIniciales {
    override val usuarios: MutableList<Usuario> = mutableListOf()

    /**
     * Carga los usuarios desde el archivo especificado en [rutaArchivo].
     * Si el archivo existe, lee las líneas, las procesa y agrega los usuarios a la lista [usuarios].
     *
     * @return `true` si los usuarios fueron cargados correctamente, `false` si hubo algún error.
     */
    override fun cargarUsuarios(): Boolean {
        if (fich.existeFichero(rutaArchivo)) {
            val listaStrings = fich.leerArchivo(rutaArchivo)

            for (linea in listaStrings) {
                val datos = linea.split(";")
                usuarios.add(Usuario.crearUsuario(datos))
            }
            return true
        }
        return false
    }

    init {
        cargarUsuarios()
    }

    /**
     * Agrega un usuario al repositorio y guarda la información en el archivo.
     *
     * @param usuario El objeto [Usuario] a agregar.
     * @return `true` si el usuario fue agregado exitosamente, `false` si no se pudo agregar.
     */
    override fun agregar(usuario: Usuario): Boolean {
        if (super.agregar(usuario)) {
            return fich.agregarLinea(rutaArchivo, usuario.serializar())
        }
        return false
    }

    /**
     * Elimina un usuario del repositorio y actualiza el archivo con los usuarios restantes.
     *
     * @param usuario El objeto [Usuario] a eliminar.
     * @return `true` si el usuario fue eliminado exitosamente, `false` si no se pudo eliminar.
     */
    override fun eliminar(usuario: Usuario): Boolean {
        if (fich.escribirArchivo(rutaArchivo, usuarios.filter { it != usuario })) {
            return super.eliminar(usuario)
        }
        return false
    }

    /**
     * Elimina un usuario por su nombre de usuario y actualiza el archivo con los usuarios restantes.
     *
     * @param nombreUsuario El nombre de usuario del usuario a eliminar.
     * @return `true` si el usuario fue eliminado exitosamente, `false` si no se pudo eliminar.
     */
    override fun eliminar(nombreUsuario: String): Boolean {
        if (fich.escribirArchivo(rutaArchivo, usuarios.filter { it.nombre != nombreUsuario })) {
            return super.eliminar(nombreUsuario)
        }
        return false
    }

    /**
     * Cambia la clave de un usuario y actualiza el archivo con la nueva lista de usuarios.
     *
     * @param usuario El objeto [Usuario] cuya clave será modificada.
     * @param nuevaClave La nueva clave para el usuario.
     * @return `true` si la clave fue cambiada exitosamente, `false` si no se pudo cambiar.
     */
    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        if (super.cambiarClave(usuario, nuevaClave)) {
            fich.escribirArchivo(rutaArchivo, usuarios)
            return true
        }
        return false
    }
}