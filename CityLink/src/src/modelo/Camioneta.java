
package modelo;

/**
 * Representa Camioneta en el sistema CityLink.
 */
public class Camioneta extends Vehiculo {

    /**
    * Construye un objeto Camioneta.
    * @param patente valor utilizado en la operación
    */
    public Camioneta(String patente) {
        super(patente, 500, 30, 300);
    }

    @Override
    /**
    * Calcula tiempoentrega según los datos del objeto.
    * @param km valor utilizado en la operación
    * @return resultado de la operación
    */
    public double calcularTiempoEntrega(double km) {
        return (km / getVelocidadKmH()) * 60 + 10;
    }
}
