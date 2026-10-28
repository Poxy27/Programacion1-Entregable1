
package excepciones;

/**
 * Representa AlmacenamientoLlenoException en el sistema CityLink.
 */
public class AlmacenamientoLlenoException extends Exception {

    /**
    * Construye un objeto AlmacenamientoLlenoException.
    * @param mensaje valor utilizado en la operación
    */
    public AlmacenamientoLlenoException(String mensaje) {
        super(mensaje);
    }
}
