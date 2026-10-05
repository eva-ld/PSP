package Ejercicios.Ej6;

import java.io.File;
import java.util.Map;

public class ConfiguradorProceso {
    public static void main(String[] args) {
        try {
            // 1. Obtener el classpath dinámico
            String classpath = System.getProperty("java.class.path");

            // ATENCIÓN: Indicamos la ruta correcta con el paquete Ej6
            String claseAElaborar = "Ejercicios.Ej6.VerificadorEntrno";

            // 2. Configurar el comando y argumentos
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", classpath, claseAElaborar, "Arg1", "Arg2");

            // 3. Establecer el directorio de trabajo
            File nuevoDir = new File(System.getProperty("java.io.tmpdir"));
            pb.directory(nuevoDir);

            // 4. Añadir la variable de entorno personalizada
            Map<String, String> env = pb.environment();
            env.put("Variable_Personalizada", "HolaDesdeProcessBuilder");

            // 5. Redirigir la salida a la consola de IntelliJ
            pb.inheritIO();

            // 6. Ejecutar y esperar
            Process proceso = pb.start();
            proceso.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}