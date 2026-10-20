/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut4.util;

/**
 *
 * @author pablo
 */

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ut4.modelo.Nave;

/**
 * Clase de utilidad para gestionar el ciclo de vida de la SessionFactory de Hibernate.
 * Aplica el patrón Singleton para garantizar una única factoría en la aplicación.
 */

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure()
                    .addAnnotatedClass(Nave.class)
                    .buildSessionFactory();

            System.out.println("✔ [HibernateUtil] SessionFactory inicializada correctamente.");

        } catch (Throwable ex) {
            System.err.println("❌ Error al inicializar Hibernate: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
            System.out.println("ℹ [HibernateUtil] SessionFactory cerrada correctamente.");
        }
    }
}


