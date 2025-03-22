package model

/**
 *
 */
enum class Riesgo(interesAplicado: Double) {
    BAJO(2.0),
    MEDIO(5.0),
    ALTO(10.0);

    /**
     *
     */
    fun getRiesgo(valor: String): Riesgo {
        return when(valor.lowercase()) {
            "bajo" -> BAJO
            "alto" -> ALTO
            else -> MEDIO
        }
    }
}