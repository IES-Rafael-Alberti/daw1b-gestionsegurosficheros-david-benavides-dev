import model.Usuario

fun main() {
    val usuario = Usuario.crearUsuario(mutableListOf("zapato","aparcador","asdf"))

    println(usuario.serializar())
}