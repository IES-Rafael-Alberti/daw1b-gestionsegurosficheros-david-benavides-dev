package model

/**
 *
 */
enum class Auto {
    COCHE, MOTO, CAMION;

    /**
     *
     */
    fun getAuto(valor: String): Auto {
        return when(valor.lowercase()) {
            "moto" -> MOTO
            "camion" -> CAMION
            else -> COCHE
        }
    }
}