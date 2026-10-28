
package modelo;

import excepciones.CapacidadExcedidaException;

/**
 * Representa Bicicleta en el sistema CityLink.
 */
public class Bicicleta extends Vehiculo {

    /**
    * Construye un objeto Bicicleta.
    * @param patente valor utilizado en la operación
    */
    public Bicicleta(String patente) {
        super(patente, 10, 15, 50);
    }

    @Override
    /**
    * Calcula tiempoentrega según los datos del objeto.
    * @param km valor utilizado en la operación
    * @return resultado de la operación
    */
    public double calcularTiempoEntrega(double km) {
        return (km / getVelocidadKmH()) * 60;
    }

    @Override
    /**
    * Verifica si el vehículo puede transportar el pedido.
    * @param pedido valor utilizado en la operación
    * @return resultado de la operación
    */
    public boolean puedeTransportar(Pedido pedido)
            throws CapacidadExcedidaException {

        if (pedido.esFragil()) {
            throw new CapacidadExcedidaException(
                    "La bicicleta no puede transportar pedidos frágiles."
            );
        }

        return super.puedeTransportar(pedido);
    }
}
