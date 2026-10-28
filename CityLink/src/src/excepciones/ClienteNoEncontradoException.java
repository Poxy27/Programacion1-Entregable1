
package excepciones;

/**
 * Representa ClienteNoEncontradoException en el sistema CityLink.
 */
public class ClienteNoEncontradoException extends Exception {

    /**
    * Construye un objeto ClienteNoEncontradoException.
    * @param mensaje valor utilizado en la operación
    */
    public ClienteNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
