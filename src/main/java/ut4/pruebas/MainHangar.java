/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut4.pruebas;

/**
 *
 * @author pablo
 */

import java.util.List;
import ut4.modelo.Nave;
import ut4.util.HibernateUtil;

public class MainHangar {

    public static void main(String[] args) {
        HangarControl control = new HangarControl();

        try {
            System.out.println("===== SISTEMA DE CONTROL DE HANGARES =====");

            // Registrar una nueva nave
            Nave nave = new Nave(
                    "TIE Interceptor",
                    "TI-v2",
                    1,
                    "Critico"
            );

            control.registrarNuevaNave(nave);

            // Actualizar su estado
            if (nave.getId() != 0) {
                control.actualizarEstadoNave(
                        nave.getId(),
                        "Estable"
                );
            }

            // Consultar las naves del modelo TI-v2
            System.out.println("\n===== ESCANEO DE CAZAS TI-v2 =====");

            List<Nave> naves = control.obtenerCazasPorModelo("TI-v2");

            if (naves.isEmpty()) {
                System.out.println("No se encontraron cazas de este modelo.");
            } else {
                for (Nave caza : naves) {
                    System.out.println(caza);
                }
            }

        } finally {
            HibernateUtil.shutdown();
        }
    }
}
