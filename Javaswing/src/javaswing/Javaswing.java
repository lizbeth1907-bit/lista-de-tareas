import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgendaApp extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JSpinner spFecha;
    private JSpinner spHora;
    private JTextField txtDescripcion;

    public AgendaApp() {
        // Configuración de la ventana
        setTitle("Agenda Personal");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ---------- Panel de entrada ----------
        JPanel panelEntrada = new JPanel(new FlowLayout());

        // Fecha
        panelEntrada.add(new JLabel("Fecha:"));
        spFecha = new JSpinner(new SpinnerDateModel());
        spFecha.setEditor(new JSpinner.DateEditor(spFecha, "dd/MM/yyyy"));
        panelEntrada.add(spFecha);

        // Hora
        panelEntrada.add(new JLabel("Hora:"));
        spHora = new JSpinner(new SpinnerDateModel());
        spHora.setEditor(new JSpinner.DateEditor(spHora, "HH:mm"));
        panelEntrada.add(spHora);

        // Descripción
        panelEntrada.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField(20);
        panelEntrada.add(txtDescripcion);

        // ---------- Tabla ----------
        String[] columnas = {"Fecha", "Hora", "Descripción"};
        modelo = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        // ---------- Botones ----------
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar seleccionado");
        JButton btnSalir = new JButton("Salir");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnSalir);

        // ---------- Añadir al Frame ----------
        add(panelEntrada, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // ---------- Eventos ----------
        btnAgregar.addActionListener(e -> agregarEvento());
        btnEliminar.addActionListener(e -> eliminarEvento());
        btnSalir.addActionListener(e -> dispose());
    }

    private void agregarEvento() {
        String descripcion = txtDescripcion.getText().trim();
        if (descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "La descripción no puede estar vacía",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Date fecha = (Date) spFecha.getValue();
        Date hora = (Date) spHora.getValue();

        String fechaStr = new SimpleDateFormat("dd/MM/yyyy").format(fecha);
        String horaStr = new SimpleDateFormat("HH:mm").format(hora);

        modelo.addRow(new Object[]{fechaStr, horaStr, descripcion});
        txtDescripcion.setText("");
        txtDescripcion.requestFocus();
    }

    private void eliminarEvento() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Selecciona un evento primero",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Eliminar el evento seleccionado?",
                "Confirmación", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            modelo.removeRow(fila);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AgendaApp().setVisible(true));
    }
}