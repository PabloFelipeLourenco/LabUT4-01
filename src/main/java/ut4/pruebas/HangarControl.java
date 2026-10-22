/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ut4.pruebas;

/**
 *
 * @author pablo
 */

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ut4.modelo.Nave;
import ut4.util.HibernateUtil;

public class HangarControl {

    public void registrarNuevaNave(Nave nave) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            session.persist(nave);

            tx.commit();
            System.out.println("Nave registrada correctamente.");
            System.out.println(nave);

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            System.out.println("Error al registrar la nave: " + e.getMessage());
        }
    }

    // Actualizar estado de naves
    public void actualizarEstadoNave(int idNave, String nuevoEstado) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Nave nave = session.get(Nave.class, idNave);

            if (nave != null) {
                nave.setEstadoSistema(nuevoEstado);
                tx.commit();

                System.out.println("Estado de la nave actualizado.");
                System.out.println(nave);
            } else {
                tx.rollback();
                System.out.println("No se encontró la nave con ID " + idNave);
            }

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            System.out.println("Error al actualizar la nave: " + e.getMessage());
        }
    }

    public List<Nave> obtenerCazasPorModelo(String modeloBuscado) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM Nave WHERE modelo = :modelo", Nave.class)
                    .setParameter("modelo", modeloBuscado)
                    .getResultList();

        } catch (Exception e) {
            System.out.println("Error al consultar las naves: " + e.getMessage());
            return List.of();
        }
    }
}
