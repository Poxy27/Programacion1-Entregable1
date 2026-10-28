
package modelo;

import excepciones.PedidoInvalidoException;

/**
 * Representa PedidoFragil en el sistema CityLink.
 */
public class PedidoFragil extends Pedido {

    /**
    * Construye un objeto PedidoFragil.
    * @param codigo valor utilizado en la operación
    * @param cliente valor utilizado en la operación
    * @param destino valor utilizado en la operación
    */
    public PedidoFragil(int codigo, Cliente cliente, String destino,
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
        return 1000 + getDistanciaKm() * vehiculo.getCostoPorKm() + 800;
    }

    @Override
    /**
    * Indica si el pedido cumple la condición indicada.
    * @return resultado de la operación
    */
    public boolean esFragil() {
        return true;
    }
}
