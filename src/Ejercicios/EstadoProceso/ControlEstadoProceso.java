package Ejercicios.EstadoProceso;

public class ControlEstadoProceso {
    public static void main(String[] args) {
        try {
            // 1. Configurar un comando externo (por ejemplo, comprobar la versión de Java)
            ProcessBuilder pb = new ProcessBuilder("java", "-version");

            System.out.println("Iniciando el proceso externo...");
            Process proceso = pb.start();

            // 2. Esperar a que el proceso externo finalice y obtener su código de salida
            int codigoSalida = proceso.waitFor();

            // 3. Evaluar el resultado y mostrar un mensaje diferente según el código
            System.out.println("\n--- RESULTADO DE LA EJECUCION ---");
            System.out.println("Codigo de salida obtenido: " + codigoSalida);

            if (codigoSalida == 0) {
                System.out.println("MENSAJE: El proceso ha finalizado CORRECTAMENTE (Éxito).");
            } else if (codigoSalida == 1) {
                System.out.println("MENSAJE: El proceso ha finalizado con un ERROR GENERAL.");
            } else {
                System.out.println("MENSAJE: El proceso ha finalizado con un codigo de advertencia o error no habitual.");
            }

        } catch (Exception e) {
            System.err.println("Ocurrio un error al intentar ejecutar el proceso: " + e.getMessage());
        }
    }
}
