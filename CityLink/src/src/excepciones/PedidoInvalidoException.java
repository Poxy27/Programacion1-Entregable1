
package excepciones;

/**
 * Representa PedidoInvalidoException en el sistema CityLink.
 */
public class PedidoInvalidoException extends Exception {

    /**
    * Construye un objeto PedidoInvalidoException.
    * @param mensaje valor utilizado en la operación
    */
    public PedidoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
