package ui

import org.jline.reader.EndOfFileException
import org.jline.reader.LineReaderBuilder
import org.jline.reader.UserInterruptException
import org.jline.terminal.TerminalBuilder

/**
 *
 */
class Consola : IEntradaSalida {
    companion object {
        const val MENSAJE_ERROR_01 = "ERROR - "
    }

    /**
     * Muestra el mensaje por consola. Si salto es true, añade un salto de línea. Si pausa es true, espera a que el usuario pulse Enter.
     */
    override fun mostrar(msj: String, salto: Boolean, pausa: Boolean) {
        if (salto) {
            println(msj)
        } else {
            print(msj)
        }
        if (pausa) {
            pausar()
        }
    }

    /**
     * Muestra el mensaje como un error anteponiendo "ERROR - ". Si ya empieza así, no lo repite.
     * Si pausar es true, llama al metodo pausar, haciendo una pausa hasta que el usuario presione una tecla.
     */
    override fun mostrarError(msj: String, pausa: Boolean) {
        if (MENSAJE_ERROR_01 in msj) {
            println(msj)
        } else {
            println(MENSAJE_ERROR_01 + msj)
        }
        if (pausa) {
            pausar()
        }
    }

    /**
     *
     */
    override fun pedirInfo(msj: String): String {
        if (msj.isNotBlank()) {
            mostrar(msj, salto = false)
        }
        return readln().trim()
    }

    /**
     *
     */
    override fun pedirInfo(msj: String, error: String, debeCumplir: (String) -> Boolean): String {

        val input = pedirInfo(msj)

        require(debeCumplir(input)) { error }

        return input
    }

    /**
     *
     */
    override fun pedirDouble(prompt: String, error: String, errorConv: String, debeCumplir: (Double) -> Boolean): Double {

        val input = pedirInfo(prompt).replace(',', '.')

        val numero = input.toDoubleOrNull()

        require(numero != null) { errorConv }

        require(debeCumplir(numero)) { error }

        return numero
    }

    /**
     *
     */
    override fun pedirEntero(prompt: String, error: String, errorConv: String, debeCumplir: (Int) -> Boolean): Int {

        val input = pedirInfo(prompt)

        val numero = input.toIntOrNull()

        require(numero is Int) { errorConv }

        require(debeCumplir(numero)) { error }

        return numero
    }

    /**
     *
     */
    override fun pedirInfoOculta(prompt: String): String {
        return try {
            val terminal = TerminalBuilder.builder()
                .dumb(true) // Para entornos no interactivos como IDEs
                .build()

            val reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build()

            reader.readLine(prompt, '*') // Oculta la contraseña con '*'
        } catch (e: UserInterruptException) {
            mostrarError("Entrada cancelada por el usuario (Ctrl + C).", pausa = false)
            ""
        } catch (e: EndOfFileException) {
            mostrarError("Se alcanzó el final del archivo (EOF ó Ctrl+D).", pausa = false)
            ""
        } catch (e: Exception) {
            mostrarError("Problema al leer la contraseña: ${e.message}", pausa = false)
            ""
        }
    }

    /**
     *
     */
    override fun pausar(msj: String) {
        print(msj)
        readln()
    }

    /**
     *
     */
    override fun limpiarPantalla(numSaltos: Int) {
        if (System.console() != null) {
            mostrar("\u001b[H\u001b[2J", false)
            System.out.flush()
        } else {
            repeat(numSaltos) {
                mostrar("")
            }
        }
    }

    /**
     *
     */
    override fun preguntar(mensaje: String): Boolean {

        var opcion = ""

        while (opcion != "s" && opcion != "n") {
            mostrar(mensaje)
            opcion = readln()
        }
        return opcion == "s"
    }

//    fun preguntar2(mensaje: String): Boolean {
//
//        var opcion = ""
//
//        while (opcion != "s" && opcion != "n") {
//            opcion.lowercase() = pedirInfo(mensaje, "error", (opcion"s"))
//        }
//        return opcion == "s"
//    }
}