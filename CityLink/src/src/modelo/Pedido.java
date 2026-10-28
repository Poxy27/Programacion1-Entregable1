
package modelo;

import java.time.LocalDateTime;
import excepciones.PedidoInvalidoException;
import excepciones.EstadoInvalidoException;

/**
 * Representa Pedido en el sistema CityLink.
 */
public abstract class Pedido implements Rastreable, Facturable {

    private int codigo;
    private Cliente cliente;
    private String destino;
    private double pesoKg;
    private double distanciaKm;
    private LocalDateTime fechaHora;
    private String estado;

    /**
    * Construye un objeto Pedido.
    * @param codigo valor utilizado en la operación
    * @param cliente valor utilizado en la operación
    * @param destino valor utilizado en la operación
    */
    public Pedido(int codigo, Cliente cliente, String destino,
                  double pesoKg, double distanciaKm)
            throws PedidoInvalidoException {

        if (codigo <= 0) {
            throw new PedidoInvalidoException(
                    "El código debe ser positivo."
            );
        }

        if (cliente == null) {
            throw new PedidoInvalidoException(
                    "El cliente no puede ser nulo."
            );
        }

        if (destino == null || destino.trim().isEmpty()) {
            throw new PedidoInvalidoException(
                    "El destino no puede estar vacío."
            );
        }

        if (pesoKg <= 0 || distanciaKm <= 0) {
            throw new PedidoInvalidoException(
                    "El peso y la distancia deben ser mayores que cero."
            );
        }

        this.codigo = codigo;
        this.cliente = cliente;
        this.destino = destino;
        this.pesoKg = pesoKg;
        this.distanciaKm = distanciaKm;
        this.fechaHora = LocalDateTime.now();
        this.estado = "PENDIENTE";
    }

    /**
    * Devuelve codigo del objeto.
    * @return resultado de la operación
    */
    public int getCodigo() {
        return codigo;
    }

    /**
    * Devuelve cliente del objeto.
    * @return resultado de la operación
    */
    public Cliente getCliente() {
        return cliente;
    }

    /**
    * Devuelve destino del objeto.
    * @return resultado de la operación
    */
    public String getDestino() {
        return destino;
    }

    /**
    * Devuelve pesokg del objeto.
    * @return resultado de la operación
    */
    public double getPesoKg() {
        return pesoKg;
    }

    /**
    * Devuelve distanciakm del objeto.
    * @return resultado de la operación
    */
    public double getDistanciaKm() {
        return distanciaKm;
    }

    /**
    * Devuelve fechahora del objeto.
    * @return resultado de la operación
    */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
    * Devuelve estado del objeto.
    * @return resultado de la operación
    */
    public String getEstado() {
        return estado;
    }

    /**
    * Calcula costo según los datos del objeto.
    * @param vehiculo valor utilizado en la operación
    * @return resultado de la operación
    */
    public abstract double calcularCosto(Vehiculo vehiculo);

    /**
    * Indica si el pedido cumple la condición indicada.
    * @return resultado de la operación
    */
    public boolean esFragil() {
        return false;
    }

    @Override
    /**
    * Devuelve estadoactual actual.
    * @return resultado de la operación
    */
    public String obtenerEstadoActual() {
        return estado;
    }

    /**
    * Cambia el estado del pedido validando la transición.
    * @param nuevoEstado valor utilizado en la operación
    */
    public void cambiarEstado(String nuevoEstado) {
        boolean valido = false;

        if (nuevoEstado == null) {
            throw new EstadoInvalidoException(
                    "El estado no puede ser nulo."
            );
        }

        if (estado.equals("PENDIENTE")) {
            valido = nuevoEstado.equals("EN_CAMINO")
                    || nuevoEstado.equals("CANCELADO");
        } else if (estado.equals("EN_CAMINO")) {
            valido = nuevoEstado.equals("ENTREGADO");
        }

        if (!valido) {
            throw new EstadoInvalidoException(
                    "No se puede cambiar de " + estado + " a " + nuevoEstado
            );
        }

        estado = nuevoEstado;
    }

    @Override
    /**
    * Calcula importe según los datos del objeto.
    * @return resultado de la operación
    */
    public double calcularImporte() {
        return 1000;
    }
private Vehiculo vehiculoAsignado;

    /**
    * Devuelve vehiculoasignado del objeto.
    * @return resultado de la operación
    */
public Vehiculo getVehiculoAsignado() {
    return vehiculoAsignado;
}

    /**
    * Establece vehiculoasignado del objeto.
    * @param vehiculo valor utilizado en la operación
    */
public void setVehiculoAsignado(Vehiculo vehiculo) {
    this.vehiculoAsignado = vehiculo;
}

@Override
    /**
    * Gestiona la operación toString del sistema.
    * @return resultado de la operación
    */
public String toString() {
    return "Pedido: " + codigo
            + " | Cliente: " + cliente.getNombre()
            + " | Destino: " + destino
            + " | Peso: " + pesoKg + " kg"
            + " | Distancia: " + distanciaKm + " km"
            + " | Estado: " + estado;
}

}
