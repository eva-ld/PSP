package Ejercicios.Ej10;

public class DestruccionProcesos {
    public static void main(String[] args) {
        try {
            // 1. Iniciar un proceso de larga duración (ej. ping de 20 segundos)
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;

            if (os.contains("win")) {
                pb = new ProcessBuilder("ping", "127.0.0.1", "-n", "20");
            } else {
                pb = new ProcessBuilder("sleep", "20");
            }

            Process proceso = pb.start();
            System.out.println("Proceso iniciado con PID: " + proceso.pid());
            System.out.println("¿Esta activo el proceso antes de solicitar su cierre?: " + proceso.isAlive());

            // Dejar correr el proceso brevemente antes de cancelarlo
            Thread.sleep(1500);

            // 2. Solicitar la finalización del proceso mediante destroy()
            System.out.println("\nSolicitando finalización normal con destroy()...");
            proceso.destroy();

            // Esperar un breve margen para que el S.O. procese la señal de cierre
            Thread.sleep(1000);

            // 3. Comprobar el estado posterior del proceso
            if (!proceso.isAlive()) {
                System.out.println("RESULTADO: El proceso ha finalizado correctamente tras la llamada a destroy().");
            } else {
                System.out.println("RESULTADO: El proceso no ha respondido a destroy() y continua activo.");
                System.out.println("Solicitando finalizacion forzada con destroyForcibly()...");

                proceso.destroyForcibly();
                proceso.waitFor(); // Esperar a que el S.O. termine de destruirlo

                System.out.println("¿Esta activo tras destroyForcibly()?: " + proceso.isAlive());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}