package model

/**
 * Enum que representa los diferentes perfiles de usuario en el sistema.
 * Los perfiles disponibles son [ADMIN], [GESTION] y [CONSULTA].
 */
enum class Perfil {
    ADMIN, GESTION, CONSULTA;

    companion object {

        /**
         * Obtiene el valor correspondiente de un perfil a partir de una cadena de texto.
         * La comparación es insensible al caso de la cadena.
         *
         * @param valor El nombre del perfil en texto (por ejemplo, "admin", "gestion", etc.).
         * @return El tipo de perfil correspondiente al valor proporcionado. Si no se encuentra
         * un valor válido, retorna [CONSULTA] por defecto.
         */
        fun getPerfil(valor: String): Perfil {
            return when (valor.lowercase()) {
                "admin" -> ADMIN
                "gestion" -> GESTION
                else -> CONSULTA
            }
        }
    }
}