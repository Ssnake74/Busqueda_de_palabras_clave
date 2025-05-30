package busqueda;

import java.util.HashSet;

public class AVLNode {
    String palabra;
    HashSet<String> documentos;
    int altura;
    AVLNode izquierda;
    AVLNode derecha;

    public AVLNode(String palabra, String documento) {
        this.palabra = palabra;
        this.documentos = new HashSet<>();
        this.documentos.add(documento);
        this.altura = 1;
    }
}