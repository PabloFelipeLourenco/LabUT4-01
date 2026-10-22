package ut4.pruebas;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ut4.modelo.Nave;
import ut4.util.HibernateUtil;

public class HangarControl {

    public void registrarNuevaNave(Nave nave) { //Clase para registrar
        Transaction tx = null; //Esta variable es la que permite controlar una los registros o transsaciones en la base de datos

        try (Session session = HibernateUtil.getSessionFactory().openSession()) { //Abre una nueva sesión de Hibernate para poder realizar operaciones con la base de datos.
            tx = session.beginTransaction(); //Iniciamos una transsacion

            session.persist(nave); //Guarda el objeto nave dentro de la base de datos //Es lo equivalente a un Update

            tx.commit(); //Sirve para guardar los cambios //Como el commit de github
            System.out.println("Nave registrada correctamente."); //Mensaje de confirmacion cuando se registre la nave
            System.out.println(nave); //Te imprime la nave que has registrado 

        } catch (Exception e) { 
            if (tx != null && tx.isActive()) { //Verifica que si da cualquier error y tx tiene cualquier tipo de dato y esta activo
                tx.rollback(); //Haga un rollback a tx para volver a ponerlo en null
            }

            System.out.println("Error al registrar la nave: " + e.getMessage()); //Mensaje de error personalizado y el dado por netbeans
        }
    }

    public void actualizarEstadoNave(int idNave, String nuevoEstado) { //Clase para actualizar
        Transaction tx = null; //Esta variable es la que permite controlar una los registros o transsaciones en la base de datos

        try (Session session = HibernateUtil.getSessionFactory().openSession()) { //Abre una nueva sesión de Hibernate para poder realizar operaciones con la base de datos.
            tx = session.beginTransaction(); //Iniciamos una transsacion

            Nave nave = session.get(Nave.class, idNave); //Sirve para buscar y recuperar una nave de la base de datos utilizando su idNave

            if (nave != null) { 
                nave.setEstadoSistema(nuevoEstado); //Actualiza el estado de la nave 
                tx.commit(); //Guarda los cambios

                System.out.println("Estado de la nave actualizado."); //Mensaje de confirmacion
                System.out.println(nave);
            } else {
                tx.rollback(); //Vuelve a poner en null la variable tx 
                System.out.println("No se encontró la nave con ID " + idNave); 
            }

        } catch (Exception e) {
            if (tx != null && tx.isActive()) { //Verifica que si da cualquier error y tx tiene cualquier tipo de dato y esta activo
                tx.rollback(); //Haga un rollback a tx para volver a ponerlo en null
            }

            System.out.println("Error al actualizar la nave: " + e.getMessage()); //Mensaje de error personalizado y el dado por netbeans
        }
    }

    public List<Nave> obtenerCazasPorModelo(String modeloBuscado) { //Funcion para buscar las naves por modelo
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery( //Consulta HQL 
                    "FROM Nave WHERE modelo = :modelo", Nave.class)
                    .setParameter("modelo", modeloBuscado) //Asigna el valor recibido por el método al parámetro de la consulta.
                    .getResultList(); //Esto sirve para que liste la consulta

        } catch (Exception e) {
            System.out.println("Error al consultar las naves: " + e.getMessage());
            return List.of(); //Si da un error esto sirve para devolver una lista vacia
        }
    }
}
