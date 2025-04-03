package app

import model.Perfil
import service.IServSeguros
import service.IServUsuarios
import ui.IEntradaSalida

/**
 * Clase encargada de gestionar el flujo de menús y opciones de la aplicación,
 * mostrando las acciones disponibles según el perfil del usuario autenticado.
 *
 * @property nombreUsuario Nombre del usuario que ha iniciado sesión.
 * @property perfilUsuario Perfil del usuario: admin, gestion o consulta.
 * @property ui Interfaz de usuario.
 * @property gestorUsuarios Servicio de operaciones sobre usuarios.
 * @property gestorSeguros Servicio de operaciones sobre seguros.
 */
class GestorMenu(val nombreUsuario: String, val perfilUsuario: Perfil, val ui: IEntradaSalida, val gestionUsuarios: IServUsuarios, val gestionSeguros: IServSeguros) {

    /**
     * Inicia un menú según el índice correspondiente al perfil actual.
     *
     * @param indice Índice del menú que se desea mostrar (0 = principal).
     */
    fun iniciarMenu(indice: Int = 0) {
        val (opciones, acciones) = ConfiguracionesApp.obtenerMenuYAcciones(perfilUsuario.name.lowercase(), indice)
        ejecutarMenu(opciones, acciones)
    }

    /**
     * Formatea el menú en forma numerada.
     */
    private fun formatearMenu(opciones: List<String>): String {
        var cadena = ""
        opciones.forEachIndexed { index, opcion ->
            cadena += "${index + 1}. $opcion\n"
        }
        return cadena
    }

    /**
     * Muestra el menú limpiando pantalla y mostrando las opciones numeradas.
     */
    private fun mostrarMenu(opciones: List<String>) {
        ui.limpiarPantalla()
        ui.mostrar(formatearMenu(opciones), salto = false)
    }

    /**
     * Ejecuta el menú interactivo.
     *
     * @param opciones Lista de opciones que se mostrarán al usuario.
     * @param ejecutar Mapa de funciones por número de opción.
     */
    private fun ejecutarMenu(opciones: List<String>, ejecutar: Map<Int, (GestorMenu) -> Boolean>) {
        do {
            mostrarMenu(opciones)
            val opcion = ui.pedirInfo("Elige opción > ").toIntOrNull()
            if (opcion != null && opcion in 1..opciones.size) {
                // Buscar en el mapa las acciones a ejecutar en la opción de menú seleccionada
                val accion = ejecutar[opcion]
                // Si la accion ejecutada del menú retorna true, debe salir del menú
                if (accion != null && accion(this)) return
            } else {
                ui.mostrarError("Opción no válida!")
            }
        } while (true)
    }

    /** Crea un nuevo usuario solicitando los datos necesarios al usuario */
    fun nuevoUsuario() {
        if (ui.preguntar("¿Desea añadir un nuevo usuario? s/n >> ")) {
            try {
                val nombre = ui.pedirInfo("Introduce el nombre del usuario >> ")
                val pwd = ui.pedirInfoOculta("Introduce su contraseña >> ")
                val perfil = ui.pedirInfo("Perfil de usuario >> ")
                gestionUsuarios.agregarUsuario(nombre, pwd, Perfil.getPerfil(perfil))
            } catch (e: IllegalArgumentException) {
                ui.mostrarError(e.message.toString())
            }
        }
    }

    /** Elimina un usuario si existe */
    fun eliminarUsuario() {
        if (ui.preguntar("¿Desea eliminar un usuario existente? s/n >> ")) {
            try {
                if (!gestionUsuarios.eliminarUsuario(ui.pedirInfo("Introduzca el nombre del usuario a eliminar >> "))) {
                    throw IllegalArgumentException("Usuario no encontrado.")
                } else {
                    ui.mostrar("Usuario eliminado con éxito.", pausa = true)
                }
            } catch (e: IllegalArgumentException) {
                ui.mostrarError(e.message.toString())
            }
        }
    }

    /** Cambia la contraseña del usuario actual */
    fun cambiarClaveUsuario() {
        val usuario = gestionUsuarios.buscarUsuario(ui.pedirInfo("Introduzca el nombre del usuario >> "))
        if (usuario != null) {
            val pwd = ui.pedirInfo("Introduzca la nueva contraseña para el usuario ${usuario.nombre} >> ")
            gestionUsuarios.cambiarClave(usuario, pwd)
        } else {
            ui.mostrarError("Usuario no encontrado.")
        }
    }

    /**
     * Mostrar la lista de usuarios (Todos o filstrados por un perfil)
     */
    fun consultarUsuarios() {
        if (ui.preguntar("¿Desea consultar la lista de usuarios? s/n >> ")) {
            for (usuario in gestionUsuarios.consultarTodos()) {
                ui.mostrar(usuario.toString(), true)
            }
        }
    }

    /**
     * Solicita al usuario un DNI y verifica que tenga el formato correcto: 8 dígitos seguidos de una letra.
     *
     * @return El DNI introducido en mayúsculas.
     */
    private fun pedirDni(): String {
        var dni: String = ""

        try {
            dni = ui.pedirInfo("Introduzca DNI > ", "DNI incorrecto") { input -> Regex("^[0-9]{8}[A-Z]$").matches(input) }
        } catch (e: IllegalArgumentException) {
            ui.mostrarError(e.message.toString())
        }
        return dni.uppercase()
    }

    /**
     * Solicita al usuario un importe positivo, usado para los seguros.
     *
     * @return El valor introducido como `Double` si es válido.
     */
    private fun pedirImporte(): Double {
        var importe: Double? = null

        while (importe == null) {
            try {
                importe = ui.pedirDouble("Introduce el importe > ", "Número incorrecto", "Error de conversión") { numero -> numero >= 0 }
            } catch (e: IllegalArgumentException) {
                ui.mostrarError(e.message.toString())
            }
        }
        return importe
    }

    /** Crea un nuevo seguro de hogar solicitando los datos al usuario */
    fun contratarSeguroHogar() {
        TODO("Implementar este método")
    }

    /** Crea un nuevo seguro de auto solicitando los datos al usuario */
    fun contratarSeguroAuto() {
        TODO("Implementar este método")
    }

    /** Crea un nuevo seguro de vida solicitando los datos al usuario */
    fun contratarSeguroVida() {
        TODO("Implementar este método")
    }

    /** Elimina un seguro si existe por su número de póliza */
    fun eliminarSeguro() {
        TODO("Implementar este método")
    }

    /** Muestra todos los seguros existentes */
    fun consultarSeguros() {
        val lista = gestionSeguros.consultarTodos()
        if (lista.isEmpty()) {
            ui.mostrar("No hay seguros existentes", pausa = true) }
        else {
            for (elemento in lista) {
                ui.mostrar(elemento.toString())
            }
        }
    }

    /** Muestra todos los seguros de tipo hogar */
    fun consultarSegurosHogar() {
        TODO("Implementar este método")
    }

    /** Muestra todos los seguros de tipo auto */
    fun consultarSegurosAuto() {
        TODO("Implementar este método")
    }

    /** Muestra todos los seguros de tipo vida */
    fun consultarSegurosVida() {
        TODO("Implementar este método")
    }

}