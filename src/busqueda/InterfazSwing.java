package busqueda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Set;

public class InterfazSwing extends JFrame {
    private JTextField campoBusqueda;
    private JTextArea areaResultados;
    private JLabel comparacionesLabel;
    private JLabel tiempoLabel;
    private JLabel totalPalabrasLabel;
    private JLabel totalBusquedasLabel;
    private AVLTree arbol;

    public InterfazSwing() {
        setTitle("Buscador de Palabras Clave");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        arbol = new AVLTree();
        JPanel panelBusqueda = new JPanel();
        campoBusqueda = new JTextField(20);
        JButton botonBuscar = new JButton("Buscar");

        panelBusqueda.add(new JLabel("Palabra clave:"));
        panelBusqueda.add(campoBusqueda);
        panelBusqueda.add(botonBuscar);

        areaResultados = new JTextArea();
        areaResultados.setEditable(false);

        JPanel panelEstadisticas = new JPanel(new GridLayout(4, 1));
        comparacionesLabel = new JLabel("Comparaciones: ");
        tiempoLabel = new JLabel("Tiempo promedio: ");
        totalPalabrasLabel = new JLabel("Palabras indexadas: ");
        totalBusquedasLabel = new JLabel("Búsquedas realizadas: ");
        panelEstadisticas.add(comparacionesLabel);
        panelEstadisticas.add(tiempoLabel);
        panelEstadisticas.add(totalPalabrasLabel);
        panelEstadisticas.add(totalBusquedasLabel);

        add(panelBusqueda, BorderLayout.NORTH);
        add(new JScrollPane(areaResultados), BorderLayout.CENTER);
        add(panelEstadisticas, BorderLayout.SOUTH);

        botonBuscar.addActionListener(e -> realizarBusqueda());

        seleccionarCarpetaYIndexar();
    }

    private void realizarBusqueda() {
        String palabra = campoBusqueda.getText().trim();
        if (palabra.isEmpty()) return;

        Set<String> resultados = arbol.buscar(palabra);
        areaResultados.setText("");

        if (resultados.isEmpty()) {
            areaResultados.append("No se encontró la palabra en ningún documento.\n");
        } else {
            areaResultados.append("Documentos que contienen la palabra:\n");
            for (String doc : resultados) {
                areaResultados.append("- " + doc + "\n");
            }
        }

        comparacionesLabel.setText("Comparaciones: " + arbol.getComparaciones());
        tiempoLabel.setText(String.format("Tiempo promedio: %.2f ms", arbol.getTiempoPromedioBusqueda()));
        totalPalabrasLabel.setText("Palabras indexadas: " + arbol.getTotalPalabras());
        totalBusquedasLabel.setText("Búsquedas realizadas: " + arbol.getTotalBusquedas());
    }

    private void seleccionarCarpetaYIndexar() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int opcion = chooser.showOpenDialog(this);

        if (opcion == JFileChooser.APPROVE_OPTION) {
            File carpeta = chooser.getSelectedFile();
            DocumentIndexer indexador = new DocumentIndexer(arbol);
            indexador.indexarDocumentos(carpeta.getAbsolutePath());
            JOptionPane.showMessageDialog(this, "Indexación completada.");
        } else {
            JOptionPane.showMessageDialog(this, "No se seleccionó ninguna carpeta.");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazSwing().setVisible(true));
    }
}