package ui

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
        if (salto) println(msj) else print(msj)
        if (pausa) pausar()
    }

    /**
     * Muestra el mensaje como un error anteponiendo "ERROR - ". Si ya empieza así, no lo repite.
     */
    override fun mostrarError(msj: String, pausa: Boolean) {
        println(MENSAJE_ERROR_01 + msj)
        if (pausa) pausar()
    }

    override fun pedirInfo(msj: String): String {
        TODO("Not yet implemented")
    }

    override fun pedirInfo(msj: String, error: String, debeCumplir: (String) -> Boolean): String {
        TODO("Not yet implemented")
    }

    override fun pedirDouble(prompt: String, error: String, errorConv: String, debeCumplir: (Double) -> Boolean): Double {
        TODO("Not yet implemented")
    }

    override fun pedirEntero(prompt: String, error: String, errorConv: String, debeCumplir: (Int) -> Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun pedirInfoOculta(prompt: String): String {
        TODO("Not yet implemented")
    }

    override fun pausar(msj: String) {
        println(msj)
        readln()
    }

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

    override fun preguntar(mensaje: String): Boolean {
        TODO("Not yet implemented")
    }
}