package Ejercicios.Ej9;

import java.util.concurrent.TimeUnit;

public class TimeoutProceso {
    public static void main(String[] args) {

        // CASO 1: El tiempo máximo (5s) es mayor que la duración del proceso (2s)
        System.out.println("=== PRUEBA 1: El proceso finaliza a tiempo ===");
        ejecutarProcesoConTimeout(2, 5);

        System.out.println("\n------------------------------------------------\n");

        // CASO 2: El tiempo máximo (2s) es menor que la duración del proceso (5s)
        System.out.println("=== PRUEBA 2: El proceso supera el tiempo limite ===");
        ejecutarProcesoConTimeout(5, 2);
    }

    private static void ejecutarProcesoConTimeout(int segundosProceso, int segundosEsperaMax) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;

            // Configurar un proceso que tarde 'segundosProceso' en finalizar
            if (os.contains("win")) {
                pb = new ProcessBuilder("ping", "127.0.0.1", "-n", String.valueOf(segundosProceso + 1));
            } else {
                pb = new ProcessBuilder("sleep", String.valueOf(segundosProceso));
            }

            Process proceso = pb.start();

            // Esperar con un tiempo límite máximo de segundosEsperaMax
            boolean haFinalizado = proceso.waitFor(segundosEsperaMax, TimeUnit.SECONDS);

            if (haFinalizado) {
                System.out.println("RESULTADO: El proceso finalizo correctamente dentro del tiempo limite.");
                System.out.println("Codigo de salida: " + proceso.exitValue());
            } else {
                System.out.println("RESULTADO: Tiempo de espera agotado. El proceso sigue en ejecucion.");
                System.out.println("Forzando la finalizacion del proceso...");
                proceso.destroyForcibly();
                System.out.println("Proceso finalizado a la fuerza.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}