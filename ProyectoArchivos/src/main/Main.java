package main;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GestorDeArchivo gestor = new GestorDeArchivo();
        ValidadorArchivo validador = new ValidadorArchivo();
        String nombreArchivo = "archivo_prueba.txt";

        try {
            // Guardar contenido en el archivo
            gestor.guardar(nombreArchivo, "Hola Liz!\nEste es un archivo de prueba.\nHecho en Java.");

            // Leer contenido del archivo
            List<String> contenido = gestor.leer(nombreArchivo);
            System.out.println("Contenido del archivo:");
            for (String linea : contenido) {
                System.out.println(linea);
            }

            // Verificar si el archivo está vacío
            validador.verificarNoVacio(nombreArchivo);
            System.out.println("✅ El archivo tiene contenido.");

        } catch (ArchivoVacioException ave) {
            System.out.println("⚠️ Error: " + ave.getMessage());
        } catch (IOException ioe) {
            System.out.println("⚠️ Error de entrada/salida: " + ioe.getMessage());
        }
    }
}
