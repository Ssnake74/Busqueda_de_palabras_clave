package busqueda;

import java.io.*;
import java.util.*;
import org.apache.poi.xwpf.usermodel.*;

public class DocumentIndexer {
    private AVLTree arbol;

    public DocumentIndexer(AVLTree arbol) {
        this.arbol = arbol;
    }

    public void indexarDocumentos(String carpetaPath) {
        File carpeta = new File(carpetaPath);
        File[] archivos = carpeta.listFiles();

        if (archivos == null) return;

        for (File archivo : archivos) {
            if (archivo.isFile()) {
                String contenido = "";

                if (archivo.getName().endsWith(".txt")) {
                    contenido = leerArchivoTxt(archivo);
                } else if (archivo.getName().endsWith(".docx")) {
                    contenido = leerArchivoDocx(archivo);
                }

                List<String> palabrasClave = WordProcessor.extraerPalabrasClave(contenido);
                for (String palabra : palabrasClave) {
                    arbol.insertar(palabra, archivo.getName());
                }
            }
        }
    }

    private String leerArchivoTxt(File archivo) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private String leerArchivoDocx(File archivo) {
        StringBuilder sb = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(archivo);
             XWPFDocument doc = new XWPFDocument(fis)) {
            for (XWPFParagraph par : doc.getParagraphs()) {
                sb.append(par.getText()).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}