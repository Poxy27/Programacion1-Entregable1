
package modelo;

import excepciones.PedidoInvalidoException;

/**
 * Representa PedidoUrgente en el sistema CityLink.
 */
public class PedidoUrgente extends Pedido {

    /**
    * Construye un objeto PedidoUrgente.
    * @param codigo valor utilizado en la operación
    * @param cliente valor utilizado en la operación
    * @param destino valor utilizado en la operación
    */
    public PedidoUrgente(int codigo, Cliente cliente, String destino,
                         double pesoKg, double distanciaKm)
            throws PedidoInvalidoException {
        super(codigo, cliente, destino, pesoKg, distanciaKm);
    }

    @Override
    /**
    * Calcula costo según los datos del objeto.
    * @param vehiculo valor utilizado en la operación
    * @return resultado de la operación
    */
    public double calcularCosto(Vehiculo vehiculo) {
        double costoBase = 1000 + getDistanciaKm() * vehiculo.getCostoPorKm();
        return costoBase * 1.5;
    }
}
