package model

/**
 *
 */
enum class Cobertura(desc: String) {
    TERCEROS("Terceros"),
    TERCEROS_AMPLIADO("Terceros +"),
    FRANQUICIA_200("Todo Riesgo con Franquicia de 200€"),
    FRANQUICIA_300("Todo Riesgo con Franquicia de 300€"),
    FRANQUICIA_400("Todo Riesgo con Franquicia de 400€"),
    FRANQUICIA_500("Todo Riesgo con Franquicia de 500€"),
    TODO_RIESGO("Todo Riesgo");

    /**
     *
     */
    fun getCobertura(valor: String): Cobertura {
        return when(valor.lowercase()) {
            "franquicia_200" -> FRANQUICIA_200
            "tercero_ampliado" -> TERCEROS_AMPLIADO
            "franquicia_300" -> FRANQUICIA_300
            "franquicia_400" -> FRANQUICIA_400
            "franquicia_500" -> FRANQUICIA_500
            "todo_riesgo" -> TODO_RIESGO
            else -> TERCEROS
        }
    }
}