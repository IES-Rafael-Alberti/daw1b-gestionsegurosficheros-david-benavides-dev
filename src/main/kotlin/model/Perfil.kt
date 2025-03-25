package model

/**
 *
 */
enum class Perfil {
    ADMIN, GESTION, CONSULTA;

    /**
     *
     */
    companion object {
        fun getPerfil(valor: String): Perfil {
            return when (valor.lowercase()) {
                "admin" -> ADMIN
                "gestion" -> GESTION
                else -> CONSULTA
            }
        }
    }
}