
package modelo;

/**
 * Representa Moto en el sistema CityLink.
 */
public class Moto extends Vehiculo {

    /**
    * Construye un objeto Moto.
    * @param patente valor utilizado en la operación
    */
    public Moto(String patente) {
        super(patente, 30, 40, 120);
    }

    @Override
    /**
    * Calcula tiempoentrega según los datos del objeto.
    * @param km valor utilizado en la operación
    * @return resultado de la operación
    */
    public double calcularTiempoEntrega(double km) {
        return (km / getVelocidadKmH()) * 60 + 5;
    }
}
