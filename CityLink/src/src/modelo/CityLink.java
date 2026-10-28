
package modelo;

import excepciones.AlmacenamientoLlenoException;
import excepciones.CapacidadExcedidaException;
import excepciones.ClienteNoEncontradoException;
import excepciones.PedidoInvalidoException;

/**
 * Representa CityLink en el sistema CityLink.
 */
public class CityLink {

    private static final int MAX_CLIENTES = 50;
    private static final int MAX_PEDIDOS = 100;
    private static final int MAX_REPARTIDORES = 10;

    private Cliente[] clientes = new Cliente[MAX_CLIENTES];
    private Pedido[] pedidos = new Pedido[MAX_PEDIDOS];
    private Repartidor[] repartidores = new Repartidor[MAX_REPARTIDORES];

    private int cantidadClientes;
    private int cantidadPedidos;
    private int cantidadRepartidores;

    /**
    * Registra cliente en el sistema.
    * @param cliente valor utilizado en la operación
    */
    public void registrarCliente(Cliente cliente)
            throws AlmacenamientoLlenoException {

        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }

        if (cantidadClientes >= MAX_CLIENTES) {
            throw new AlmacenamientoLlenoException("No hay espacio para más clientes.");
        }

        for (int i = 0; i < cantidadClientes; i++) {
            if (clientes[i].getCodigo() == cliente.getCodigo()) {
                throw new IllegalArgumentException("El código de cliente ya existe.");
            }
        }

        clientes[cantidadClientes++] = cliente;
    }

    /**
    * Busca cliente según el criterio indicado.
    * @param codigo valor utilizado en la operación
    * @return resultado de la operación
    */
    public Cliente buscarCliente(int codigo)
            throws ClienteNoEncontradoException {

        for (int i = 0; i < cantidadClientes; i++) {
            if (clientes[i].getCodigo() == codigo) {
                return clientes[i];
            }
        }

        throw new ClienteNoEncontradoException(
                "No se encontró un cliente con código " + codigo
        );
    }

    /**
    * Busca cliente según el criterio indicado.
    * @param nombre valor utilizado en la operación
    * @return resultado de la operación
    */
    public Cliente buscarCliente(String nombre)
            throws ClienteNoEncontradoException {

        if (nombre != null) {
            for (int i = 0; i < cantidadClientes; i++) {
                if (clientes[i].getNombre().equalsIgnoreCase(nombre.trim())) {
                    return clientes[i];
                }
            }
        }

        throw new ClienteNoEncontradoException(
                "No se encontró el cliente: " + nombre
        );
    }

    /**
    * Registra repartidor en el sistema.
    * @param repartidor valor utilizado en la operación
    */
    public void registrarRepartidor(Repartidor repartidor)
            throws AlmacenamientoLlenoException {

        if (repartidor == null) {
            throw new IllegalArgumentException("El repartidor no puede ser nulo.");
        }

        if (cantidadRepartidores >= MAX_REPARTIDORES) {
            throw new AlmacenamientoLlenoException("No hay espacio para más repartidores.");
        }

        for (int i = 0; i < cantidadRepartidores; i++) {
            if (repartidores[i].getLegajo() == repartidor.getLegajo()) {
                throw new IllegalArgumentException("El legajo ya existe.");
            }
        }

        repartidores[cantidadRepartidores++] = repartidor;
    }

    /**
    * Registra pedido en el sistema.
    * @param pedido valor utilizado en la operación
    */
    public void registrarPedido(Pedido pedido)
            throws AlmacenamientoLlenoException,
                   ClienteNoEncontradoException,
                   PedidoInvalidoException {

        if (pedido == null) {
            throw new IllegalArgumentException("El pedido no puede ser nulo.");
        }

        if (cantidadPedidos >= MAX_PEDIDOS) {
            throw new AlmacenamientoLlenoException("No hay espacio para más pedidos.");
        }

        buscarCliente(pedido.getCliente().getCodigo());

        for (int i = 0; i < cantidadPedidos; i++) {
            if (pedidos[i].getCodigo() == pedido.getCodigo()) {
                throw new PedidoInvalidoException("El código de pedido ya existe.");
            }
        }

        pedidos[cantidadPedidos++] = pedido;
    }

    /**
    * Busca pedido según el criterio indicado.
    * @param codigo valor utilizado en la operación
    * @return resultado de la operación
    */
    public Pedido buscarPedido(int codigo) {
        for (int i = 0; i < cantidadPedidos; i++) {
            if (pedidos[i].getCodigo() == codigo) {
                return pedidos[i];
            }
        }

        return null;
    }

    /**
    * Busca repartidor según el criterio indicado.
    * @param legajo valor utilizado en la operación
    * @return resultado de la operación
    */
    public Repartidor buscarRepartidor(int legajo) {
        for (int i = 0; i < cantidadRepartidores; i++) {
            if (repartidores[i].getLegajo() == legajo) {
                return repartidores[i];
            }
        }

        return null;
    }

    /**
    * Asigna el pedido al repartidor.
    * @param pedido valor utilizado en la operación
    */
    public void asignarPedido(Pedido pedido)
            throws AlmacenamientoLlenoException,
                   CapacidadExcedidaException {

        CapacidadExcedidaException ultimoError = null;

        for (int i = 0; i < cantidadRepartidores; i++) {
            try {
                repartidores[i].asignarPedido(pedido);

                if (pedido.getEstado().equals("PENDIENTE")) {
                    pedido.cambiarEstado("EN_CAMINO");
                }

                return;

            } catch (CapacidadExcedidaException e) {
                ultimoError = e;
            }
        }

        if (ultimoError != null) {
            throw ultimoError;
        }

        throw new CapacidadExcedidaException(
                "No hay repartidores disponibles para transportar el pedido."
        );
    }

    /**
    * Asigna el pedido al repartidor.
    * @param pedido valor utilizado en la operación
    * @param vehiculo valor utilizado en la operación
    */
    public void asignarPedido(Pedido pedido, Vehiculo vehiculo)
            throws AlmacenamientoLlenoException,
                   CapacidadExcedidaException {

        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehículo no puede ser nulo.");
        }

        for (int i = 0; i < cantidadRepartidores; i++) {
            if (repartidores[i].getVehiculo().getPatente()
                    .equalsIgnoreCase(vehiculo.getPatente())) {

                repartidores[i].asignarPedido(pedido);

                if (pedido.getEstado().equals("PENDIENTE")) {
                    pedido.cambiarEstado("EN_CAMINO");
                }

                return;
            }
        }

        throw new CapacidadExcedidaException(
                "No existe un repartidor registrado con ese vehículo."
        );
    }

    /**
    * Registra entrega en el sistema.
    * @param codigoPedido valor utilizado en la operación
    * @param legajo valor utilizado en la operación
    */
    public void registrarEntrega(int codigoPedido, int legajo) {
        Pedido pedido = buscarPedido(codigoPedido);
        Repartidor repartidor = buscarRepartidor(legajo);

        if (pedido == null) {
            throw new IllegalArgumentException("No existe ese pedido.");
        }

        if (repartidor == null) {
            throw new IllegalArgumentException("No existe ese repartidor.");
        }

        repartidor.registrarEntrega(pedido);
    }

    /**
    * Cambia el estado del pedido validando la transición.
    * @param codigo valor utilizado en la operación
    * @param estado valor utilizado en la operación
    */
    public void cambiarEstadoPedido(int codigo, String estado) {
        Pedido pedido = buscarPedido(codigo);

        if (pedido == null) {
            throw new IllegalArgumentException("No existe ese pedido.");
        }

        pedido.cambiarEstado(estado);
    }

    /**
    * Devuelve clientes del objeto.
    * @return resultado de la operación
    */
    public Cliente[] getClientes() {
        Cliente[] copia = new Cliente[cantidadClientes];

        for (int i = 0; i < cantidadClientes; i++) {
            copia[i] = clientes[i];
        }

        return copia;
    }

    /**
    * Devuelve pedidos del objeto.
    * @return resultado de la operación
    */
    public Pedido[] getPedidos() {
        Pedido[] copia = new Pedido[cantidadPedidos];

        for (int i = 0; i < cantidadPedidos; i++) {
            copia[i] = pedidos[i];
        }

        return copia;
    }

    /**
    * Devuelve repartidores del objeto.
    * @return resultado de la operación
    */
    public Repartidor[] getRepartidores() {
        Repartidor[] copia = new Repartidor[cantidadRepartidores];

        for (int i = 0; i < cantidadRepartidores; i++) {
            copia[i] = repartidores[i];
        }

        return copia;
    }

    /**
    * Calcula importetotalpedidos según los datos del objeto.
    * @return resultado de la operación
    */
    public double calcularImporteTotalPedidos() {
        double total = 0;

        for (int i = 0; i < cantidadPedidos; i++) {
            total += pedidos[i].calcularImporte();
        }

        return total;
    }
}
