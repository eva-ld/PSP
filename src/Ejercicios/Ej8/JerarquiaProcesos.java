package Ejercicios.Ej8;

import java.util.Iterator;
import java.util.Optional;

public class JerarquiaProcesos {
    public static void main(String[] args) {
        try {
            // 1. Obtener información del proceso actual (el programa Java en ejecución)
            ProcessHandle procesoActual = ProcessHandle.current();
            System.out.println("=== PROCESO ACTUAL (PADRE DE NUESTRO PROGRAMA) ===");
            System.out.println("PID Proceso Actual: " + procesoActual.pid());

            // 2. Obtener y mostrar la información del proceso Padre (ej. IntelliJ o la consola)
            Optional<ProcessHandle> procesoPadre = procesoActual.parent();
            if (procesoPadre.isPresent()) {
                System.out.println("PID del Proceso Padre: " + procesoPadre.get().pid());
            } else {
                System.out.println("No se ha encontrado proceso padre.");
            }

            // 3. Crear un proceso Hijo que tarde unos segundos en terminar
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;

            if (os.contains("win")) {
                pb = new ProcessBuilder("ping", "127.0.0.1", "-n", "6");
            } else {
                pb = new ProcessBuilder("sleep", "5");
            }

            Process procesoHijo = pb.start();

            // 4. Mostrar el PID del proceso hijo recién creado
            System.out.println("\n=== PROCESO HIJO CREADO ===");
            System.out.println("PID Proceso Hijo: " + procesoHijo.pid());
            System.out.println("¿El proceso hijo esta activo?: " + procesoHijo.isAlive());

            // 5. Consultar la lista de hijos del proceso actual
            System.out.println("\n=== LISTA DE PROCESOS HIJO DETECTADOS ===");
            Iterator<ProcessHandle> iteradorHijos = procesoActual.children().iterator();
            while (iteradorHijos.hasNext()) {
                ProcessHandle hijo = iteradorHijos.next();
                System.out.println("-> Hijo detectado con PID: " + hijo.pid() + " | Activo: " + hijo.isAlive());
            }

            // 6. Esperar a que el proceso hijo finalice
            procesoHijo.waitFor();
            System.out.println("\nEl proceso hijo ha finalizado.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
