package busqueda;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazSwing ventana = new InterfazSwing();
            ventana.setVisible(true);
        });
    }
}