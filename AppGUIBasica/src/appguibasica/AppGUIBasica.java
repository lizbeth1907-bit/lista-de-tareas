import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AppGUIBasica extends JFrame {

    private JTextField campoTexto;
    private JButton btnAgregar, btnLimpiar;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaDatos;

    public AppGUIBasica() {
        // Configuración de la ventana principal
        setTitle("Aplicación GUI Básica - Lista de Datos");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana
        setLayout(new BorderLayout(10, 10));

        // Panel superior con etiqueta y campo de texto
        JPanel panelSuperior = new JPanel(new FlowLayout());
        JLabel lblTexto = new JLabel("Ingrese un dato:");
        campoTexto = new JTextField(15);
        panelSuperior.add(lblTexto);
        panelSuperior.add(campoTexto);

        // Panel central con lista
        modeloLista = new DefaultListModel<>();
        listaDatos = new JList<>(modeloLista);
        JScrollPane scrollLista = new JScrollPane(listaDatos);

        // Panel inferior con botones
        JPanel panelInferior = new JPanel(new FlowLayout());
        btnAgregar = new JButton("Agregar");
        btnLimpiar = new JButton("Limpiar");
        panelInferior.add(btnAgregar);
        panelInferior.add(btnLimpiar);

        // Agregar los paneles al layout principal
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollLista, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // Eventos
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = campoTexto.getText().trim();
                if (!texto.isEmpty()) {
                    modeloLista.addElement(texto);
                    campoTexto.setText("");
                } else {
                    JOptionPane.showMessageDialog(AppGUIBasica.this,
                            "El campo está vacío. Ingrese un dato válido.",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoTexto.setText("");
                modeloLista.clear();
            }
        });
    }

    public static void main(String[] args) {
        // Establecer apariencia nativa del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.out.println("No se pudo aplicar LookAndFeel del sistema.");
        }

        // Ejecutar la aplicación
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AppGUIBasica().setVisible(true);
            }
        });
    }
}