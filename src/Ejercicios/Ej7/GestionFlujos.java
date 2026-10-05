package Ejercicios.Ej7;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GestionFlujos {
    public static void main(String[] args) {
        try {
            // 1. Crear un comando que escriba en la salida estándar y en la salida de error
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;

            if (os.contains("win")) {
                // En Windows: echo para stdout y echo con 1>&2 para stderr
                pb = new ProcessBuilder("cmd.exe", "/c", "echo Salida normal por STDOUT & echo Mensaje de aviso por STDERR 1>&2");
            } else {
                // En Linux / macOS
                pb = new ProcessBuilder("sh", "-c", "echo Salida normal por STDOUT; echo Mensaje de aviso por STDERR >&2");
            }

            // 2. Iniciar el proceso
            Process proceso = pb.start();

            // 3. Crear un hilo para leer la salida de errores (STDERR) de forma independiente
            Thread hiloError = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        BufferedReader readerError = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
                        String lineaError;
                        while ((lineaError = readerError.readLine()) != null) {
                            System.err.println("[FLUJO DE ERROR - STDERR]: " + lineaError);
                        }
                        readerError.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            // Iniciar la lectura del flujo de error en segundo plano
            hiloError.start();

            // 4. Leer la salida estándar (STDOUT) en el hilo principal
            BufferedReader readerOutput = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String lineaOutput;
            while ((lineaOutput = readerOutput.readLine()) != null) {
                System.out.println("[FLUJO ESTANDAR - STDOUT]: " + lineaOutput);
            }
            readerOutput.close();

            // 5. Esperar a que el hilo de error y el proceso terminen
            hiloError.join();
            proceso.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}