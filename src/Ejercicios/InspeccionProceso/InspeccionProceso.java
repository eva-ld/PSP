package Ejercicios.InspeccionProceso;

import java.lang.ProcessHandle.Info;

public class InspeccionProceso {
    public static void main(String[] args) {
        try {
            // 1. Determinar el sistema operativo para ejecutar un proceso duradero
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;

            if (os.contains("win")) {
                // Paga 5 pings a la dirección local (tarda unos 4-5 segundos)
                pb = new ProcessBuilder("ping", "127.0.0.1", "-n", "5");
            } else {
                // En Linux/macOS esperamos 5 segundos
                pb = new ProcessBuilder("sleep", "5");
            }

            // 2. Iniciar el proceso externo
            Process proceso = pb.start();

            // 3. Obtener el ProcessHandle del proceso iniciado
            ProcessHandle handle = proceso.toHandle();

            // 4. Consultar y mostrar información del proceso mientras se ejecuta
            System.out.println("=== INFORMACION DEL PROCESO EN EJECUCION ===");
            System.out.println("PID del proceso: " + handle.pid());
            System.out.println("¿El proceso esta activo?: " + handle.isAlive());

            // Obtener objeto Info con detalles del proceso
            Info info = handle.info();

            if (info.command().isPresent()) {
                System.out.println("Comando ejecutado: " + info.command().get());
            }

            if (info.user().isPresent()) {
                System.out.println("Usuario del sistema: " + info.user().get());
            }

            if (info.startInstant().isPresent()) {
                System.out.println("Momento de inicio: " + info.startInstant().get());
            }

            // 5. Esperar a que el proceso termine
            proceso.waitFor();

            // 6. Comprobar el estado tras su finalización
            System.out.println("\n=== ESTADO TRAS FINALIZAR ===");
            System.out.println("¿El proceso continua activo?: " + handle.isAlive());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}