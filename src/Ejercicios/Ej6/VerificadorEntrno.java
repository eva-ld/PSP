package Ejercicios.Ej6;

public class VerificadorEntrno {
    public static void main(String[] args) {
        System.out.println("=== COMPROBACION EN EL PROCESO HIJO ===");

        // 1. Mostrar directorio de trabajo actual
        System.out.println("Directorio de trabajo actual: " + System.getProperty("user.dir"));

        // 2. Mostrar valor de la variable de entorno personalizada
        String miVariable = System.getenv("Variable_Personalizada");
        System.out.println("Variable Variable_Personalizada: " + miVariable);

        // 3. Mostrar los argumentos recibidos
        System.out.println("Argumentos recibidos:");
        for (int i = 0; i < args.length; i++) {
            System.out.println(" - Argumento [" + i + "]: " + args[i]);
        }
    }
}
