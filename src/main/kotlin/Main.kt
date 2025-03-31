import data.RepoUsuariosFich
import data.RepoUsuariosMem
import model.Perfil
import model.SeguroHogar
import model.Usuario
import ui.Consola
import utils.Ficheros

fun main() {
    // ************ PRUEBAS ****************
//    val usuario = Usuario.crearUsuario(mutableListOf("a","prueba","admin"))
//
//    println(usuario.serializar())
//
//    val seguro = SeguroHogar("48718498B",0.0,0,0.0,"",0)
//
//    println(seguro.tipoSeguro())
//
//    val usuariosFichero = RepoUsuariosFich("src/main/kotlin/data/res/Usuarios.txt", Ficheros(Consola()))
//
//    usuariosFichero.agregar(Usuario("prueba","asdfa", Perfil.ADMIN))
//    usuariosFichero.agregar(Usuario("pruebl23la","asdafdfa", Perfil.ADMIN))
//    usuariosFichero.agregar(Usuario("pruebl23la5ytfd","asaddfa", Perfil.ADMIN))
//
//    println("\n")
//
//    val todoUsuarios = usuariosFichero.obtenerTodos()
//    for(usuario in todoUsuarios) {
//        println(usuario.serializar())
//    }
//
//    println("\n")
//
//    println(usuariosFichero.buscar("prueba")!!.serializar())
//    usuariosFichero.eliminar("pruebl23la")
//
//    println("\n")
//
//    for(usuario in todoUsuarios) {
//        println(usuario.serializar())
//    }
//    println("\n")
//    println(usuariosFichero.usuarios[1].serializar())
//    usuariosFichero.cambiarClave(usuariosFichero.usuarios[1], "AAAAAAAAAAAAAAAA")
//    println(usuariosFichero.usuarios[1].serializar())
//    println("\n")
//    println(usuariosFichero.usuarios[1].verificarClave("AAAAAAAAAAAAAABAA"))
//

//    while (true) {
//
//        readln()
//        println(usuariosFichero.cargarUsuarios())
//        println(usuariosFichero.obtenerTodos())
//
//
//    }
}