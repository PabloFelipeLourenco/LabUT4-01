package ut4.pruebas;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ut4.modelo.Planeta;
import ut4.util.HibernateUtil;

public class PlanetarioControl {

    // Persiste el planeta y, por cascade ALL, también sus naves
    public void registrarNuevoPlaneta(Planeta planeta) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(planeta);
            tx.commit();
            System.out.println("Planeta registrado correctamente: " + planeta);

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Error al registrar el planeta: " + e.getMessage());
        }
    }

    // Recupera un planeta por ID con su lista de naves ya cargada
    public Planeta obtenerPlanetaPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Planeta planeta = session.get(Planeta.class, id);
            if (planeta != null) {
                Hibernate.initialize(planeta.getListaNaves()); // carga la colección LAZY
            }
            return planeta;

        } catch (Exception e) {
            System.out.println("Error al recuperar el planeta: " + e.getMessage());
            return null;
        }
    }
}