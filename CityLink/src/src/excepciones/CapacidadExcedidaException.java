
package excepciones;

/**
 * Representa CapacidadExcedidaException en el sistema CityLink.
 */
public class CapacidadExcedidaException extends Exception {

    /**
    * Construye un objeto CapacidadExcedidaException.
    * @param mensaje valor utilizado en la operación
    */
    public CapacidadExcedidaException(String mensaje) {
        super(mensaje);
    }
}
