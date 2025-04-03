package model

/**
 * Enum que representa los diferentes tipos de vehículos.
 * Los tipos disponibles son [COCHE], [MOTO] y [CAMION].
 */
enum class Auto {
    COCHE, MOTO, CAMION;

    companion object {

        /**
         * Obtiene el valor correspondiente del tipo de vehículo a partir de una cadena de texto.
         * La comparación es insensible al caso de la cadena.
         *
         * @param valor El nombre del tipo de vehículo en texto (por ejemplo, "moto", "camion", etc.).
         * @return El tipo de vehículo correspondiente a la cadena proporcionada. Si no se encuentra
         * un valor válido, retorna [COCHE] por defecto.
         */
        fun getAuto(valor: String): Auto {
            return when (valor.lowercase()) {
                "moto" -> MOTO
                "camion" -> CAMION
                else -> COCHE
            }
        }
    }
}