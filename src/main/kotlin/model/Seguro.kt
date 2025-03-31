package model

/**
 * Clase abstracta que representa un seguro. Esta clase almacena los detalles generales del seguro,
 * como el número de póliza, el DNI del titular y el importe del seguro. Además, define métodos para
 * calcular el importe del siguiente año, serializar los datos del seguro, y obtener los detalles del seguro.
 *
 * @param numPoliza El número único de la póliza de seguro.
 * @param dniTitular El DNI del titular del seguro.
 * @param importe El importe del seguro.
 */
abstract class Seguro(var numPoliza: Int, private var dniTitular: String, protected var importe: Double): IExportable {
    init {
        require(dniTitular.matches(Regex("^[0-9]{8}[A-Z]$"))) { "*ERROR* DNI incorrecto." }
    }

    /**
     * Metodo abstracto que debe ser implementado por las clases derivadas para calcular el importe
     * del seguro para el año siguiente, tomando en cuenta un interés determinado.
     *
     * @param interes El porcentaje de interés a aplicar.
     * @return El importe calculado para el año siguiente, considerando el interés aplicado.
     */
    abstract fun calcularImporteAnioSiguiente(interes: Double): Double

    /**
     * Obtiene el tipo de seguro basado en la clase actual.
     * El tipo se obtiene del nombre simple de la clase.
     *
     * @return El nombre de la clase (tipo de seguro), o "Desconocido" si no se puede obtener el nombre.
     */
    fun tipoSeguro(): String {
        return this::class.simpleName ?: "Desconocido"
    }

    /**
     * Serializa los datos del seguro a una cadena de texto utilizando el separador proporcionado.
     * Los datos serializados incluyen el número de póliza, el DNI del titular y el importe del seguro.
     *
     * @param separador El carácter o cadena que se utilizará para separar los datos.
     * @return Una cadena de texto con los datos serializados del seguro.
     */
    override fun serializar(separador: String): String {
        return "$numPoliza$separador$dniTitular$separador$importe"
    }

    /**
     * Obtiene los datos del seguro en un formato de cadena de texto, para ser utilizados en la
     * representación de la clase o en otras operaciones.
     *
     * @return Una cadena con los datos esenciales del seguro (número de póliza, DNI del titular e importe).
     */
    protected fun obtenerDatosSeguro(): String {
        return "numPoliza=$numPoliza, dniTitular=$dniTitular, importe=${"%.2f".format(importe)}"
    }

    /**
     * Devuelve una representación en cadena del objeto [Seguro] con todos sus datos.
     *
     * @return Una cadena con todos los datos del seguro.
     */
    override fun toString(): String {
        return "Seguro(${obtenerDatosSeguro()})"
    }

    /**
     * Calcula el valor hash del objeto [Seguro], basado en el número de póliza y el DNI del titular.
     * Se utiliza para la comparación de objetos en colecciones como conjuntos (Set).
     *
     * @return El código hash calculado para este objeto.
     */
    override fun hashCode(): Int {
        return numPoliza.hashCode() + dniTitular.hashCode()
    }


    /**
     * Compara este objeto [Seguro] con otro objeto para determinar si son iguales.
     * Dos objetos de tipo [Seguro] son iguales si tienen el mismo número de póliza y el mismo DNI del titular.
     *
     * @param other El objeto con el cual se va a comparar.
     * @return `true` si los objetos son iguales, de lo contrario `false`.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Seguro) return false
        return numPoliza == other.numPoliza && dniTitular == other.dniTitular
    }
}