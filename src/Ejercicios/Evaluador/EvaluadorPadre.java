package Ejercicios.Evaluador;

public class EvaluadorPadre {
    public static void main(String[] args) {
        // Array con diferentes valores para probar el comportamiento
        String[] pruebas = {"OK", "AVISO", "ERROR", "OTRO"};

        // Obtener la ruta de las clases compiladas dinámicamente
        String classpath = System.getProperty("java.class.path");
        String claseAElaborar = "Ejercicios.Suma.EvaluadorCodigo";

        for (int i = 0; i < pruebas.length; i++) {
            String arg = pruebas[i];
            try {
                // Configurar y lanzar el proceso pasándole el argumento
                ProcessBuilder pb = new ProcessBuilder("java", "-cp", classpath, claseAElaborar, arg);
                Process proceso = pb.start();

                // Esperar a que el proceso secundario termine y recoger el código de salida
                int codigoSalida = proceso.waitFor();

                System.out.println("Argumento enviado: '" + arg + "' -> Código de salida recibido: " + codigoSalida);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
