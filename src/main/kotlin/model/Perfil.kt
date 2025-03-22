package model

/**
 *
 */
enum class Perfil {
    ADMIN, GESTION, CONSULTA;

    /**
     *
     */
    fun getPerfil(valor: String): Perfil {
        return when(valor.lowercase()) {
            "admin" -> ADMIN
            "gestion" -> GESTION
            else -> CONSULTA
        }
    }
}