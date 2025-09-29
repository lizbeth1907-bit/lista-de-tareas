import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Clase principal
public class ListaDeTareasApp extends JFrame {

    private DefaultListModel<String> modeloLista;  // Modelo para almacenar las tareas
    private JList<String> listaTareas;             // Componente visual de la lista
    private JTextField campoTexto;                // Campo para ingresar nuevas tareas

    public ListaDeTareasApp() {
        setTitle("Lista de Tareas");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Campo de texto para ingresar tareas
        campoTexto = new JTextField();
        campoTexto.setToolTipText("Escribe una nueva tarea y presiona Enter");
        panel.add(campoTexto, BorderLayout.NORTH);

        // Modelo y lista
        modeloLista = new DefaultListModel<>();
        listaTareas = new JList<>(modeloLista);
        listaTareas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaTareas.setCellRenderer(new TareaRenderer());
        JScrollPane scrollPane = new JScrollPane(listaTareas);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Añadir Tarea");
        JButton btnCompletar = new JButton("Marcar como Completada");
        JButton btnEliminar = new JButton("Eliminar Tarea");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnCompletar);
        panelBotones.add(btnEliminar);
        panel.add(panelBotones, BorderLayout.SOUTH);

        // Agrega el panel al frame
        add(panel);

        // Acción al presionar Enter en el campo de texto
        campoTexto.addActionListener(e -> agregarTarea());

        // Acción al hacer clic en el botón "Añadir Tarea"
        btnAgregar.addActionListener(e -> agregarTarea());

        // Acción al hacer clic en el botón "Marcar como Completada"
        btnCompletar.addActionListener(e -> marcarComoCompletada());

        // Acción al hacer clic en el botón "Eliminar Tarea"
        btnEliminar.addActionListener(e -> eliminarTarea());

        // (Opcional) Doble clic para marcar como completada
        listaTareas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    marcarComoCompletada();
                }
            }
        });
    }

    // Método para agregar una tarea
    private void agregarTarea() {
        String texto = campoTexto.getText().trim();
        if (!texto.isEmpty()) {
            modeloLista.addElement(texto);
            campoTexto.setText("");
        }
    }

    // Método para marcar una tarea como completada (añade el prefijo "[Completada]" si no lo tiene)
    private void marcarComoCompletada() {
        int index = listaTareas.getSelectedIndex();
        if (index != -1) {
            String tarea = modeloLista.getElementAt(index);
            if (!tarea.startsWith("[Completada] ")) {
                modeloLista.setElementAt("[Completada] " + tarea, index);
            }
        }
    }

    // Método para eliminar una tarea seleccionada
    private void eliminarTarea() {
        int index = listaTareas.getSelectedIndex();
        if (index != -1) {
            modeloLista.remove(index);
        }
    }

    // Renderer personalizado para mostrar tareas completadas con texto tachado y gris
    private class TareaRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(
                JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);

            String texto = value.toString();
            if (texto.startsWith("[Completada] ")) {
                label.setText("<html><strike>" + texto + "</strike></html>");
                label.setForeground(Color.GRAY);
            } else {
                label.setText(texto);
                label.setForeground(Color.BLACK);
            }

            return label;
        }
    }

    // Método principal para lanzar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ListaDeTareasApp().setVisible(true);
        });
    }
}