package ut4.pruebas;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ut4.modelo.Planeta;
import ut4.util.HibernateUtil;

public class PlanetarioControl {

    // Registra el planeta y también sus naves gracias a cascade ALL
    public void registrarNuevoPlaneta(Planeta planeta) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Iniciamos la transacción y guardamos el planeta
            tx = session.beginTransaction();
            session.persist(planeta);
            tx.commit();

            System.out.println("Planeta registrado correctamente: " + planeta);

        } catch (Exception e) {
            // Deshacemos los cambios si ocurre un error
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Error al registrar el planeta: " + e.getMessage());
        }
    }

    // Busca un planeta por su ID
    public Planeta obtenerPlanetaPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Planeta planeta = session.get(Planeta.class, id);

            // Cargamos las naves antes de cerrar la sesión
            if (planeta != null) {
                Hibernate.initialize(planeta.getListaNaves());
            }
            return planeta;

        } catch (Exception e) {
            System.out.println("Error al recuperar el planeta: " + e.getMessage());
            return null;
        }
    }
}