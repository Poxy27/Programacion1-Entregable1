
package excepciones;

/**
 * Representa EstadoInvalidoException en el sistema CityLink.
 */
public class EstadoInvalidoException extends RuntimeException {

    /**
    * Construye un objeto EstadoInvalidoException.
    * @param mensaje valor utilizado en la operación
    */
    public EstadoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
