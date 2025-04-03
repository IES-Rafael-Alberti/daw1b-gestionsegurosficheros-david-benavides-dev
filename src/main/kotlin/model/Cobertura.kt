package model

/**
 * Enum que representa los diferentes tipos de coberturas disponibles para seguros.
 * Cada cobertura tiene una descripción asociada.
 */
enum class Cobertura(val desc: String) {
    TERCEROS("Terceros"),
    TERCEROS_AMPLIADO("Terceros +"),
    FRANQUICIA_200("Todo Riesgo con Franquicia de 200€"),
    FRANQUICIA_300("Todo Riesgo con Franquicia de 300€"),
    FRANQUICIA_400("Todo Riesgo con Franquicia de 400€"),
    FRANQUICIA_500("Todo Riesgo con Franquicia de 500€"),
    TODO_RIESGO("Todo Riesgo");

    companion object {

        /**
         * Obtiene el valor correspondiente de la cobertura basado en una cadena de texto.
         * La comparación es insensible al caso de la cadena.
         *
         * @param valor El nombre de la cobertura en texto (por ejemplo, "terceros", "franquicia_300", etc.).
         * @return El tipo de cobertura correspondiente al valor proporcionado. Si no se encuentra
         * un valor válido, retorna [TERCEROS] por defecto.
         */
        fun getCobertura(valor: String): Cobertura {
            return when (valor.lowercase()) {
                "franquicia_200" -> FRANQUICIA_200
                "terceros_ampliado" -> TERCEROS_AMPLIADO
                "franquicia_300" -> FRANQUICIA_300
                "franquicia_400" -> FRANQUICIA_400
                "franquicia_500" -> FRANQUICIA_500
                "todo_riesgo" -> TODO_RIESGO
                else -> TERCEROS
            }
        }
    }
}