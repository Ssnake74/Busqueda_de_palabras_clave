package busqueda;

import java.util.Set;
import java.util.Scanner;

public class SearchEngine {
    public static void main(String[] args) {
        AVLTree arbol = new AVLTree();
        Scanner sc = new Scanner(System.in);

        DocumentIndexer indexador = new DocumentIndexer(arbol);
        indexador.indexarDocumentos("ruta/a/la/carpeta"); // <-- cambia esta ruta según tu proyecto

        while (true) {
            System.out.print("Ingrese palabra clave (o 'salir'): ");
            String palabra = sc.nextLine().trim();
            if (palabra.equalsIgnoreCase("salir")) break;

            Set<String> documentos = arbol.buscar(palabra);

            if (documentos.isEmpty()) {
                System.out.println("Palabra no encontrada.");
            } else {
                System.out.println("Palabra encontrada en:");
                for (String doc : documentos) {
                    System.out.println("- " + doc);
                }
            }

            System.out.println("Comparaciones: " + arbol.getComparaciones());
            System.out.printf("Tiempo promedio: %.2f ms\n", arbol.getTiempoPromedioBusqueda());
        }

        sc.close();
    }
}