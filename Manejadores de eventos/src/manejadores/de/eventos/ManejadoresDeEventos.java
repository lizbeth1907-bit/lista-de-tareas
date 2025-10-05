import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CapturaTeclaC extends JFrame {

    private JList<String> lista;
    private JButton boton;
    private DefaultListModel<String> modeloLista;

    public CapturaTeclaC() {
        setTitle("Captura de Tecla 'C'");
        setSize(350, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Modelo de la lista
        modeloLista = new DefaultListModel<>();
        modeloLista.addElement("Elemento 1");
        modeloLista.addElement("Elemento 2");
        modeloLista.addElement("Elemento 3");

        // Crear lista y botón
        lista = new JList<>(modeloLista);
        boton = new JButton("Presiona 'C' para acción");

        // Agregamos componentes a la ventana
        add(new JScrollPane(lista), BorderLayout.CENTER);
        add(boton, BorderLayout.SOUTH);

        // Listener de teclado (KeyAdapter)
        lista.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Detecta si la tecla presionada es 'C' o 'c'
                if (e.getKeyChar() == 'c' || e.getKeyChar() == 'C') {
                    int index = lista.getSelectedIndex();
                    if (index != -1) {
                        // Acción al presionar C sobre un elemento
                        String seleccionado = modeloLista.getElementAt(index);
                        System.out.println("Se presionó 'C' sobre: " + seleccionado);
                        JOptionPane.showMessageDialog(null,
                                "Tecla 'C' detectada en: " + seleccionado);
                    } else {
                        System.out.println("Presionaste 'C' sin seleccionar ningún elemento.");
                    }
                }
            }
        });

        // Hacemos que la lista tenga el foco para recibir el evento de teclado
        lista.setFocusable(true);
        lista.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CapturaTeclaC().setVisible(true);
        });
    }
}