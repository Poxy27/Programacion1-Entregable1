
package modelo;

/**
 * Representa Vehiculo en el sistema CityLink.
 */
public abstract class Vehiculo {

    private String patente;
    private double capacidadKg;
    private double velocidadKmH;
    private double costoPorKm;

    /**
    * Construye un objeto Vehiculo.
    * @param patente valor utilizado en la operación
    * @param capacidadKg valor utilizado en la operación
    */
    public Vehiculo(String patente, double capacidadKg,
                    double velocidadKmH, double costoPorKm) {
        this.patente = patente;
        this.capacidadKg = capacidadKg;
        this.velocidadKmH = velocidadKmH;
        this.costoPorKm = costoPorKm;
    }
    
    /**
    * Devuelve patente del objeto.
    * @return resultado de la operación
    */
    public String getPatente() {
        return patente;
    }

    /**
    * Devuelve capacidadkg del objeto.
    * @return resultado de la operación
    */
    public double getCapacidadKg() {
        return capacidadKg;
    }

    /**
    * Devuelve velocidadkmh del objeto.
    * @return resultado de la operación
    */
    public double getVelocidadKmH() {
        return velocidadKmH;
    }

    /**
    * Devuelve costoporkm del objeto.
    * @return resultado de la operación
    */
    public double getCostoPorKm() {
        return costoPorKm;
    }

    /**
    * Calcula tiempoentrega según los datos del objeto.
    * @param km valor utilizado en la operación
    * @return resultado de la operación
    */
    public abstract double calcularTiempoEntrega(double km);

    
    

    /**
    * Verifica si el vehículo puede transportar el pedido.
    * @param pedido valor utilizado en la operación
    * @return resultado de la operación
    */
public boolean puedeTransportar(Pedido pedido)
        throws excepciones.CapacidadExcedidaException {

    if (pedido.getPesoKg() > capacidadKg) {
        throw new excepciones.CapacidadExcedidaException(
                "El vehículo no tiene capacidad suficiente para este pedido."
        );
    }

    return true;
}

}
