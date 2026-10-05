package Ejercicios.RedireccionFicheros;

import java.io.File;

public class EjecutorComando {

    public static void main(String[] args) {

        ProcessBuilder pb = new ProcessBuilder(
                "cmd", "/c", "dir", "ficheroQueNoExiste.txt"
        );

        pb.redirectOutput(new File("salida.txt"));
        pb.redirectError(new File("errores.txt"));

        try {

            Process proceso = pb.start();

            proceso.waitFor();

            System.out.println("Proceso terminado.");

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
    }
}