
package modelo;

/**
 * Representa Repartidor en el sistema CityLink.
 */
public class Repartidor implements Rastreable, Facturable {

    private int legajo;
    private String nombre;
    private Vehiculo vehiculo;
    private Pedido[] pedidosAsignados;
    private int cantidadPedidos;

    /**
    * Construye un objeto Repartidor.
    * @param legajo valor utilizado en la operación
    * @param nombre valor utilizado en la operación
    * @param vehiculo valor utilizado en la operación
    */
    public Repartidor(int legajo, String nombre, Vehiculo vehiculo) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.vehiculo = vehiculo;
        this.pedidosAsignados = new Pedido[100];
        this.cantidadPedidos = 0;
    }

    /**
    * Devuelve legajo del objeto.
    * @return resultado de la operación
    */
    public int getLegajo() {
        return legajo;
    }

    /**
    * Devuelve nombre del objeto.
    * @return resultado de la operación
    */
    public String getNombre() {
        return nombre;
    }

    /**
    * Devuelve vehiculo del objeto.
    * @return resultado de la operación
    */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    
    /**
    * Asigna el pedido al repartidor.
    * @param pedido valor utilizado en la operación
    */
public void asignarPedido(Pedido pedido)
        throws excepciones.AlmacenamientoLlenoException,
               excepciones.CapacidadExcedidaException {

    if (cantidadPedidos >= pedidosAsignados.length) {
        throw new excepciones.AlmacenamientoLlenoException(
                "No hay espacio para más pedidos."
        );
    }

    vehiculo.puedeTransportar(pedido);

    pedido.setVehiculoAsignado(vehiculo);

    pedidosAsignados[cantidadPedidos] = pedido;
    cantidadPedidos++;
}



    /**
    * Registra entrega en el sistema.
    * @param pedido valor utilizado en la operación
    */
public void registrarEntrega(Pedido pedido) {
    boolean asignado = false;

    for (int i = 0; i < cantidadPedidos; i++) {
        if (pedidosAsignados[i].getCodigo() == pedido.getCodigo()) {
            asignado = true;
            break;
        }
    }

    if (!asignado) {
        throw new IllegalArgumentException(
                "El pedido no está asignado a este repartidor."
        );
    }

    pedido.cambiarEstado("ENTREGADO");
}


    @Override
    /**
    * Devuelve estadoactual actual.
    * @return resultado de la operación
    */
public String obtenerEstadoActual() {
    return "Repartidor: " + nombre;
}

@Override
    /**
    * Calcula importe según los datos del objeto.
    * @return resultado de la operación
    */
public double calcularImporte() {
    double total = 0;

    for (int i = 0; i < cantidadPedidos; i++) {
        if (pedidosAsignados[i].getEstado().equals("ENTREGADO")) {
            total += pedidosAsignados[i].calcularCosto(vehiculo);
        }
    }

    return total * 0.10;
}

}
