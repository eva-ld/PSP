package Ejercicios.Evaluador;

public class EvaluadorCodigo {
    public static void main(String[] args) {
        // Si no se recibe ningún parámetro, se termina con código 1 (Error)
        if (args.length == 0) {
            System.exit(1);
        }

        String valor = args[0];

        // Se evalúa el texto recibido para devolver un código distinto
        if (valor.equalsIgnoreCase("OK")) {
            System.exit(0); // 0 = Finalización correcta
        } else if (valor.equalsIgnoreCase("AVISO")) {
            System.exit(2); // 2 = Advertencia / Valor especial
        } else if (valor.equalsIgnoreCase("ERROR")) {
            System.exit(3); // 3 = Error controlado
        } else {
            System.exit(99); // 99 = Valor desconocido
        }
    }
}
