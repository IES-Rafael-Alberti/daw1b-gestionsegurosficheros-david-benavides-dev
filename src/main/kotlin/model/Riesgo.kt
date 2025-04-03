package model

/**
 * Enum que representa los diferentes niveles de riesgo aplicados en un seguro.
 * Los niveles de riesgo disponibles son [BAJO], [MEDIO] y [ALTO], cada uno con un interés aplicado correspondiente.
 */
enum class Riesgo(val interesAplicado: Double) {
    BAJO(2.0),
    MEDIO(5.0),
    ALTO(10.0);

    companion object {

        /**
         * Obtiene el valor correspondiente de un nivel de riesgo a partir de una cadena de texto.
         * La comparación es insensible al caso de la cadena.
         *
         * @param valor El nombre del nivel de riesgo en texto (por ejemplo, "bajo", "alto", etc.).
         * @return El tipo de riesgo correspondiente al valor proporcionado. Si no se encuentra
         * un valor válido, retorna [MEDIO] por defecto.
         */
        fun getRiesgo(valor: String): Riesgo {
            return when (valor.lowercase()) {
                "bajo" -> BAJO
                "alto" -> ALTO
                else -> MEDIO
            }
        }
    }
}