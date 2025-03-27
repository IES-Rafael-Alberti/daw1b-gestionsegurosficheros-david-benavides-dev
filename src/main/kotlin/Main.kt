import model.SeguroHogar
import model.Usuario

fun main() {
    val usuario = Usuario.crearUsuario(mutableListOf("a","aparcador","admin"))

    println(usuario.serializar())

    val seguro = SeguroHogar("48718498B",0.0,0,0.0,"",0)

    println(seguro.tipoSeguro())
}