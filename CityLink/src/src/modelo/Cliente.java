package modelo;

/**
 * Representa Cliente en el sistema CityLink.
 */
public class Cliente {

    private int codigo;
    private String nombre;
    private String telefono;
    private String zona;

    
    /**
    * Construye un objeto Cliente.
    */
    public Cliente() {
    }
    
    /**
    * Construye un objeto Cliente.
    * @param codigo valor utilizado en la operación
    * @param nombre valor utilizado en la operación
    * @param telefono valor utilizado en la operación
    * @param zona valor utilizado en la operación
    */
    public Cliente(int codigo, String nombre, String telefono, String zona) {
        setCodigo(codigo);
        setNombre(nombre);
        setTelefono(telefono);
        setZona(zona);
    }
    
    /**
    * Devuelve codigo del objeto.
    * @return resultado de la operación
    */
    public int getCodigo() {
        return codigo;
    }

    /**
    * Devuelve nombre del objeto.
    * @return resultado de la operación
    */
    public String getNombre() {
        return nombre;
    }

    /**
    * Devuelve telefono del objeto.
    * @return resultado de la operación
    */
    public String getTelefono() {
        return telefono;
    }

    /**
    * Devuelve zona del objeto.
    * @return resultado de la operación
    */
    public String getZona() {
        return zona;
    }
    

    /**
    * Establece codigo del objeto.
    * @param codigo valor utilizado en la operación
    */
    public void setCodigo(int codigo) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("El código debe ser positivo.");
        }
        this.codigo = codigo;
    }

    /**
    * Establece nombre del objeto.
    * @param nombre valor utilizado en la operación
    */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    /**
    * Establece telefono del objeto.
    * @param telefono valor utilizado en la operación
    */
    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        }
        this.telefono = telefono;
    }

    /**
    * Establece zona del objeto.
    * @param zona valor utilizado en la operación
    */
    public void setZona(String zona) {
        if (zona == null || zona.trim().isEmpty()) {
            throw new IllegalArgumentException("La zona no puede estar vacía.");
        }
        this.zona = zona;
    }

@Override
    /**
    * Gestiona la operación toString del sistema.
    * @return resultado de la operación
    */
public String toString() {
    return "Código: " + codigo
            + " | Nombre: " + nombre
            + " | Teléfono: " + telefono
            + " | Zona: " + zona;
}

}    