package ut4.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ut4.modelo.Nave;

public class HibernateUtil { //Clase de utilidad para gestionar el ciclo de vida de la SessionFactory de Hibernate. //Garantiza una única factoría en la aplicacion.

    private static final SessionFactory sessionFactory; //Sirve para manejar las utilidades de Hibernate

        static { //Se utiliza para ejecutar codigo automaticamente cuando se inicializa una clase
        try {
            sessionFactory = new Configuration() //Este objeto permite indicar cómo debe funcionar Hibernate: qué entidades utilizar, como conectarse a la base de datos, etc.
                    .configure() //Carga la configuracion de Hibernate
                    .addAnnotatedClass(Nave.class) // Le indica a Hibernate que debe registrar la clase Nave como una entidad que debe conocer.
                    .buildSessionFactory(); //Sirve para configurar las sesiones futuras

            System.out.println("[HibernateUtil] SessionFactory inicializada correctamente.");

        } catch (Throwable ex) {
            System.err.println("Error al inicializar Hibernate: " + ex);
            throw new ExceptionInInitializerError(ex); //Lanza un error que indica que la inicializacion de una clase ha fallado
        }
    }

    public static SessionFactory getSessionFactory() { //Esta funcion sirve para permitir que otras clases de tu aplicación puedan utilizarla para abrir sesiones con la base de datos.
        return sessionFactory;
    }

    public static void shutdown() { //Funcion para cerrar sesiones
        if (sessionFactory != null && !sessionFactory.isClosed()) { //Comprueba si esta abierta la base de datos
            sessionFactory.close(); //Si esta abierto lo cierra
            System.out.println("ℹ [HibernateUtil] SessionFactory cerrada correctamente.");
        }
    }
}


