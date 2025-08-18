package gestorarchivos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorDeArchivo {
    public void guardar(String nombreArchivo, String contenido) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(contenido);
        }
    }

    public List<String> leer(String nombreArchivo) throws IOException {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineas.add(linea);
            }
        }
        return lineas;
    }
}
