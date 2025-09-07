import java.util.*;

public class Main {

    // Clase Libro
    static class Libro {
        String isbn;
        String titulo;
        String autor;
        String categoria;

        Libro(String isbn, String titulo, String autor, String categoria) {
            this.isbn = isbn;
            this.titulo = titulo;
            this.autor = autor;
            this.categoria = categoria;
        }

        @Override
        public String toString() {
            return "[" + isbn + "] " + titulo + " - " + autor + " (" + categoria + ")";
        }
    }

    // Clase Usuario
    static class Usuario {
        String id;
        String nombre;
        List<String> prestados = new ArrayList<>();

        Usuario(String id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }
    }

    // Clase Biblioteca
    static class Biblioteca {
        Map<String, Libro> catalogo = new HashMap<>();
        Map<String, Usuario> usuarios = new HashMap<>();
        Set<String> enUso = new HashSet<>();

        void agregarLibro(Libro l) {
            catalogo.put(l.isbn, l);
        }

        void registrarUsuario(Usuario u) {
            usuarios.put(u.id, u);
        }

        void prestar(String idU, String isbn) {
            Usuario u = usuarios.get(idU);
            Libro l = catalogo.get(isbn);

            if (u == null || l == null) {
                System.out.println("Usuario o libro no existe");
                return;
            }
            if (enUso.contains(isbn)) {
                System.out.println("El libro ya esta prestado");
                return;
            }

            u.prestados.add(isbn);
            enUso.add(isbn);
            System.out.println(u.nombre + " presto: " + l.titulo);
        }

        void devolver(String idU, String isbn) {
            Usuario u = usuarios.get(idU);
            if (u != null && u.prestados.remove(isbn)) {
                enUso.remove(isbn);
                System.out.println("Libro devuelto: " + isbn);
            } else {
                System.out.println("El usuario no tenia este libro");
            }
        }

        void listarPrestados(String idU) {
            Usuario u = usuarios.get(idU);
            if (u != null) {
                System.out.println();
                System.out.println("Libros de " + u.nombre + ":");
                for (String isbn : u.prestados) {
                    System.out.println(catalogo.get(isbn));
                }
            }
        }
    }

    // Main de prueba
    public static void main(String[] args) {
        Biblioteca b = new Biblioteca();

        // Registrar usuarios
        b.registrarUsuario(new Usuario("U1", "Ana"));
        b.registrarUsuario(new Usuario("U2", "Luis"));

        // Agregar libros
        b.agregarLibro(new Libro("L1", "Clean Code", "Robert Martin", "Software"));
        b.agregarLibro(new Libro("L2", "Effective Java", "Joshua Bloch", "Java"));

        // Prestar libros
        b.prestar("U1", "L1");
        b.prestar("U2", "L2");

        // Listar libros prestados de Ana
        b.listarPrestados("U1");

        // Devolver libro
        b.devolver("U1", "L1");
    }
}