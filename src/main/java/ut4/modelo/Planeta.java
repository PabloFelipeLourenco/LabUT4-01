package ut4.modelo;
import jakarta.persistence.*;
import java.io.Serializable;
import ut4.modelo.Nave;

@Entity
@Table(name = "planetas")
public class Planeta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planet_id")
    private int id;

    @Column(name = "nombre_planeta", nullable = false, length = 100)
    private String nombrePlaneta;

    @Column(name = "sector_galactico", nullable = false, length = 50)
    private String sectorGalactico;
    
    @OneToMany(mappedBy = "planetaOrigen", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private java.util.List<Nave> listaNaves = new java.util.ArrayList<>();


    // Constructor obligatorio JPA
    public Planeta() {}

    public Planeta(String nombrePlaneta, String sectorGalactico) {
        this.nombrePlaneta = nombrePlaneta;
        this.sectorGalactico = sectorGalactico;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombrePlaneta() { return nombrePlaneta; }
    public void setNombrePlaneta(String nombrePlaneta) { this.nombrePlaneta = nombrePlaneta; }
    public String getSectorGalactico() { return sectorGalactico; }
    public void setSectorGalactico(String sectorGalactico) { this.sectorGalactico = sectorGalactico; }
    public java.util.List<Nave> getListaNaves() { return listaNaves; }
    public void setListaNaves(java.util.List<Nave> listaNaves) { this.listaNaves = listaNaves; }


    @Override
    public String toString() {
        return "Planeta [ID=" + id + ", Nombre=" + nombrePlaneta + ", Sector=" + sectorGalactico + "]";
    }
    
    public void agregarNave(Nave nave) {
        this.listaNaves.add(nave);
        nave.setPlanetaOrigen(this); // Sincroniza la clave ajena de forma automática en la Nave
    }
}
