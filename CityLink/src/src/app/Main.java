
package app;

import java.util.Scanner;

import modelo.*;
import excepciones.*;

/**
 * Representa Main en el sistema CityLink.
 */
public class Main {

    private static final Scanner TECLADO = new Scanner(System.in);
    private static final CityLink SISTEMA = new CityLink();

    /**
    * Inicia la aplicación y muestra el menú principal.
    */
    public static void main(String[] args) {
        precargarDatos();

        int opcion = 0;

        while (opcion != 10) {
            mostrarMenu();

            try {
                opcion = leerEntero("Elegí una opción: ");

                switch (opcion) {
                    case 1:
                        altaCliente();
                        break;
                    case 2:
                        altaRepartidor();
                        break;
                    case 3:
                        altaPedido();
                        break;
                    case 4:
                        asignarPedido();
                        break;
                    case 5:
                        registrarEntregaOCambiarEstado();
                        break;
                    case 6:
                        buscarCliente();
                        break;
                    case 7:
                        listarPedidos();
                        break;
                    case 8:
                        consultarEstado();
                        break;
                    case 9:
                        mostrarFacturacion();
                        break;
                    case 10:
                        System.out.println("Hasta luego.");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Ingresá un número válido.");

            } catch (ClienteNoEncontradoException
                     | PedidoInvalidoException
                     | CapacidadExcedidaException
                     | AlmacenamientoLlenoException e) {

                System.out.println("Error: " + e.getMessage());

            } catch (IllegalArgumentException
                     | EstadoInvalidoException e) {

                System.out.println("Error: " + e.getMessage());

            } finally {
                // Se ejecuta al finalizar cada operación del menú.
            }
        }

        TECLADO.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n===== CITYLINK =====");
        System.out.println("1. Registrar cliente");
        System.out.println("2. Registrar repartidor");
        System.out.println("3. Registrar pedido");
        System.out.println("4. Asignar pedido");
        System.out.println("5. Cambiar estado o registrar entrega");
        System.out.println("6. Buscar cliente");
        System.out.println("7. Listar pedidos");
        System.out.println("8. Consultar estado");
        System.out.println("9. Facturación y comisiones");
        System.out.println("10. Salir");
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Integer.parseInt(TECLADO.nextLine().trim());
    }

    private static double leerDouble(String mensaje) {
        System.out.print(mensaje);
        return Double.parseDouble(
                TECLADO.nextLine().trim().replace(',', '.')
        );
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return TECLADO.nextLine().trim();
    }

    private static void precargarDatos() {
        try {
            Cliente c1 = new Cliente(1, "Ana Pérez", "2664000001", "Centro");
            Cliente c2 = new Cliente(2, "Luis Gómez", "2664000002", "Estación");
            Cliente c3 = new Cliente(3, "Marta Díaz", "2664000003", "Norte");
            Cliente c4 = new Cliente(4, "Juan López", "2664000004", "Sur");
            Cliente c5 = new Cliente(5, "Sofía Ruiz", "2664000005", "Oeste");

            SISTEMA.registrarCliente(c1);
            SISTEMA.registrarCliente(c2);
            SISTEMA.registrarCliente(c3);
            SISTEMA.registrarCliente(c4);
            SISTEMA.registrarCliente(c5);

            SISTEMA.registrarRepartidor(
                    new Repartidor(101, "Pedro", new Bicicleta("A100AAA"))
            );
            SISTEMA.registrarRepartidor(
                    new Repartidor(102, "Lucía", new Moto("B200BBB"))
            );
            SISTEMA.registrarRepartidor(
                    new Repartidor(103, "Marcos", new Camioneta("C300CCC"))
            );

            SISTEMA.registrarPedido(
                    new PedidoNormal(1001, c1, "Plaza central", 2, 3)
            );
            SISTEMA.registrarPedido(
                    new PedidoUrgente(1002, c2, "Hospital", 4, 5)
            );
            SISTEMA.registrarPedido(
                    new PedidoFragil(1003, c3, "Barrio Norte", 3, 7)
            );
            SISTEMA.registrarPedido(
                    new PedidoNormal(1004, c4, "Terminal", 5, 4)
            );
            SISTEMA.registrarPedido(
                    new PedidoUrgente(1005, c5, "Universidad", 1, 6)
            );
            SISTEMA.registrarPedido(
                    new PedidoFragil(1006, c1, "Barrio Sur", 8, 2)
            );
            SISTEMA.registrarPedido(
                    new PedidoNormal(1007, c2, "Municipalidad", 12, 8)
            );
            SISTEMA.registrarPedido(
                    new PedidoUrgente(1008, c3, "Parque", 6, 10)
            );

        } catch (Exception e) {
            System.out.println("Error al precargar datos: " + e.getMessage());
        }
    }

    private static void altaCliente()
            throws AlmacenamientoLlenoException {

        int codigo = leerEntero("Código: ");
        String nombre = leerTexto("Nombre: ");
        String telefono = leerTexto("Teléfono: ");
        String zona = leerTexto("Zona: ");

        SISTEMA.registrarCliente(
                new Cliente(codigo, nombre, telefono, zona)
        );

        System.out.println("Cliente registrado.");
    }

    private static void altaRepartidor()
            throws AlmacenamientoLlenoException {

        int legajo = leerEntero("Legajo: ");
        String nombre = leerTexto("Nombre: ");

        System.out.println("1. Bicicleta  2. Moto  3. Camioneta");
        int tipo = leerEntero("Tipo de vehículo: ");
        String patente = leerTexto("Patente: ");

        Vehiculo vehiculo;

        switch (tipo) {
            case 1:
                vehiculo = new Bicicleta(patente);
                break;
            case 2:
                vehiculo = new Moto(patente);
                break;
            case 3:
                vehiculo = new Camioneta(patente);
                break;
            default:
                throw new IllegalArgumentException(
                        "Tipo de vehículo inválido."
                );
        }

        SISTEMA.registrarRepartidor(
                new Repartidor(legajo, nombre, vehiculo)
        );

        System.out.println("Repartidor registrado.");
    }

    private static void altaPedido()
            throws ClienteNoEncontradoException,
                   PedidoInvalidoException,
                   AlmacenamientoLlenoException {

        int codigo = leerEntero("Código del pedido: ");
        int codigoCliente = leerEntero("Código del cliente: ");
        Cliente cliente = SISTEMA.buscarCliente(codigoCliente);

        String destino = leerTexto("Destino: ");
        double peso = leerDouble("Peso en kg: ");
        double distancia = leerDouble("Distancia en km: ");

        System.out.println("1. Normal  2. Urgente  3. Frágil");
        int tipo = leerEntero("Tipo de pedido: ");

        Pedido pedido;

        switch (tipo) {
            case 1:
                pedido = new PedidoNormal(
                        codigo, cliente, destino, peso, distancia
                );
                break;
            case 2:
                pedido = new PedidoUrgente(
                        codigo, cliente, destino, peso, distancia
                );
                break;
            case 3:
                pedido = new PedidoFragil(
                        codigo, cliente, destino, peso, distancia
                );
                break;
            default:
                throw new IllegalArgumentException(
                        "Tipo de pedido inválido."
                );
        }

        SISTEMA.registrarPedido(pedido);
        System.out.println("Pedido registrado.");
    }

    
private static void asignarPedido()
        throws AlmacenamientoLlenoException,
               CapacidadExcedidaException {

    int codigo = leerEntero("Código del pedido: ");
    Pedido pedido = SISTEMA.buscarPedido(codigo);

    if (pedido == null) {
        System.out.println("No existe ese pedido.");
        return;
    }

    Repartidor[] repartidores = SISTEMA.getRepartidores();

    System.out.println("Repartidores disponibles:");

    for (Repartidor repartidor : repartidores) {
        if (repartidor != null) {
            System.out.println(
                    repartidor.getLegajo() + ". "
                    + repartidor.getNombre() + " - "
                    + repartidor.getVehiculo()
                            .getClass().getSimpleName()
            );
        }
    }

    int legajo = leerEntero("Ingresá el legajo del repartidor: ");
    Repartidor elegido = SISTEMA.buscarRepartidor(legajo);

    if (elegido == null) {
        System.out.println("No existe ese repartidor.");
        return;
    }

    elegido.asignarPedido(pedido);
    System.out.println("Pedido asignado correctamente.");
}

    private static void registrarEntregaOCambiarEstado() {
        int codigo = leerEntero("Código del pedido: ");
        Pedido pedido = SISTEMA.buscarPedido(codigo);

        if (pedido == null) {
            System.out.println("No existe ese pedido.");
            return;
        }

        System.out.println("1. Pasar a EN_CAMINO");
        System.out.println("2. Registrar entrega");
        System.out.println("3. Cancelar pedido");

        int opcion = leerEntero("Elegí una opción: ");

        if (opcion == 1) {
            pedido.cambiarEstado("EN_CAMINO");
        } else if (opcion == 2) {
            int legajo = leerEntero("Legajo del repartidor: ");
            SISTEMA.registrarEntrega(codigo, legajo);
        } else if (opcion == 3) {
            pedido.cambiarEstado("CANCELADO");
        } else {
            System.out.println("Opción inválida.");
            return;
        }

        System.out.println("Estado actual: " + pedido.getEstado());
    }

    private static void buscarCliente()
            throws ClienteNoEncontradoException {

        System.out.println("1. Buscar por código");
        System.out.println("2. Buscar por nombre");

        int opcion = leerEntero("Elegí una opción: ");
        Cliente cliente;

        if (opcion == 1) {
            cliente = SISTEMA.buscarCliente(
                    leerEntero("Código: ")
            );
        } else if (opcion == 2) {
            cliente = SISTEMA.buscarCliente(
                    leerTexto("Nombre: ")
            );
        } else {
            System.out.println("Opción inválida.");
            return;
        }

        System.out.println(cliente);
    }

    private static void listarPedidos() {
        Pedido[] pedidos = SISTEMA.getPedidos();

        if (pedidos.length == 0) {
            System.out.println("No hay pedidos registrados.");
            return;
        }

        for (Pedido pedido : pedidos) {
            System.out.println(pedido);

            Vehiculo vehiculo = pedido.getVehiculoAsignado();

            if (vehiculo != null) {
                System.out.printf(
                        "Costo: $%.2f | Tiempo estimado: %.1f minutos%n",
                        pedido.calcularCosto(vehiculo),
                        vehiculo.calcularTiempoEntrega(
                                pedido.getDistanciaKm()
                        )
                );
            } else {
                System.out.println("Pedido sin vehículo asignado.");
            }
        }
    }

    private static void consultarEstado() {
        System.out.println("1. Pedido  2. Repartidor");
        int opcion = leerEntero("Elegí una opción: ");

        if (opcion == 1) {
            int codigo = leerEntero("Código del pedido: ");
            Pedido pedido = SISTEMA.buscarPedido(codigo);

            System.out.println(
                    pedido == null
                            ? "Pedido inexistente."
                            : pedido.obtenerEstadoActual()
            );

        } else if (opcion == 2) {
            int legajo = leerEntero("Legajo del repartidor: ");
            Repartidor repartidor = SISTEMA.buscarRepartidor(legajo);

            System.out.println(
                    repartidor == null
                            ? "Repartidor inexistente."
                            : repartidor.obtenerEstadoActual()
            );

        } else {
            System.out.println("Opción inválida.");
        }
    }

    
private static void mostrarFacturacion() {
    System.out.printf(
            "Importe total de pedidos: $%.2f%n",
            SISTEMA.calcularImporteTotalPedidos()
    );

    for (Repartidor repartidor : SISTEMA.getRepartidores()) {
        System.out.printf(
                "Repartidor: %s | Vehículo: %s | Comisión: $%.2f%n",
                repartidor.getNombre(),
                repartidor.getVehiculo().getClass().getSimpleName(),
                repartidor.calcularImporte()
        );
    }
}

}
