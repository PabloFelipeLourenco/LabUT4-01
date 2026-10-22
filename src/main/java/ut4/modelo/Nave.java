package ut4.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

@Entity  //Clase Persistente (Entidad) que mapea la tabla 'naves' de PostgreSQL.
@Table(name = "naves") // Vincula la clase con la tabla exacta de la BD
public class Nave implements Serializable {

    //Clave Primaria que se autogenera, no es necesario ponerla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Mapea el tipo 'SERIAL' de Postgrest
    @Column(name = "nave_id") // Vincula el atributo con el nombre exacto de la columna
    private int id;

    //Columnas obligatorias (que no pueden ser null)
    @Column(name = "nombre", nullable = false, length = 100) //Asi es como se crea la columna nombre //nullable la hace obligatoria //lenght dice el numero maximo de caracteres que puede tener el campo
    private String nombre; //Se crea tambien la variable nombre

    @Column(name = "modelo", nullable = false, length = 50) //Asi es como se crea la columna modelo
    private String modelo; //Se crea tambien la variable modelo

    @Column(name = "capacidad_pasajeros", nullable = false) //Asi es como se crea la columna capacidad_pasajeros
    private int capacidadPasajeros; //Se crea tambien la variable capacidadPasajeros

    @Column(name = "estado_sistema", nullable = false, length = 20) //Asi es como se crea la columna estado_sistema
    private String estadoSistema; //Se crea tambien la variable estadoSistema
    
    @ManyToOne(fetch = FetchType.LAZY) // LAZY evita cargar el planeta de la BD a menos que se invoque explícitamente
    @JoinColumn(name = "planet_id", foreignKey = @ForeignKey(name = "FK_NAVE_PLANETA"))
    private Planeta planetaOrigen;

    
    //Constructor vacio
    public Nave() {
    }

    //Constructor hecho para pasarle los parametros
    public Nave(String nombre, String modelo, int capacidadPasajeros, String estadoSistema, Planeta planetaOrigen) {
        this.nombre = nombre;
        this.modelo = modelo;
        this.capacidadPasajeros = capacidadPasajeros;
        this.estadoSistema = estadoSistema;
        this.planetaOrigen = planetaOrigen;
    }


    //Getter y Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getCapacidadPasajeros() { return capacidadPasajeros; }
    public void setCapacidadPasajeros(int capacidadPasajeros) { this.capacidadPasajeros = capacidadPasajeros; }

    public String getEstadoSistema() { return estadoSistema; }
    public void setEstadoSistema(String estadoSistema) { this.estadoSistema = estadoSistema; }
    
    public Planeta getPlanetaOrigen() { return planetaOrigen; }
    public void setPlanetaOrigen(Planeta planetaOrigen) { this.planetaOrigen = planetaOrigen; }


    //Mensaje personalizado paa imprimir una nave en consola
    @Override
    public String toString() {
        return "[Nave Persistida] ID: " + id + " | " + nombre + " (" + modelo + ") - Estado: " + estadoSistema;
    }
}
