package Ejercicios.Suma;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class SumaPadre {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el primer número:");
        int numero1 = sc.nextInt();

        System.out.println("Introduce el segundo número:");
        int numero2 = sc.nextInt();

        ProcessBuilder pb = new ProcessBuilder(
                "java",
                "Ejercicios.Suma"
        );

        Process proceso = pb.start();

        OutputStream entrada = proceso.getOutputStream();

        entrada.write((numero1 + "\n").getBytes());
        entrada.write((numero2 + "\n").getBytes());

        entrada.flush();
        entrada.close();

        BufferedReader salida = new BufferedReader(
                new InputStreamReader(proceso.getInputStream())
        );

        String respuesta = salida.readLine();

        System.out.println("Respuesta del programa Suma:");
        System.out.println(respuesta);

        proceso.waitFor();
    }
}