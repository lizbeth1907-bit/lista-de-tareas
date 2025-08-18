package gestorarchivos;

import java.io.*;

public class ValidadorArchivo {
    public void verificarNoVacio(String nombreArchivo) throws IOException, ArchivoVacioException {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            if (reader.readLine() == null) {
                throw new ArchivoVacioException("El archivo '" + nombreArchivo + "' está vacío.");
            }
        }
    }
}
