package model

/**
 *
 */
enum class Auto {
    COCHE, MOTO, CAMION;

    /**
     *
     */
    companion object {
        fun getAuto(valor: String): Auto {
            return when (valor.lowercase()) {
                "moto" -> MOTO
                "camion" -> CAMION
                else -> COCHE
            }
        }
    }
}