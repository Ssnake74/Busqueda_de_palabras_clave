package busqueda;

import java.util.*;
import java.util.regex.*;

public class WordProcessor {

    public static List<String> extraerPalabrasClave(String contenido) {
        List<String> palabrasClave = new ArrayList<>();
        Matcher matcher = Pattern.compile("\\b\\w+\\b").matcher(contenido.toLowerCase());

        while (matcher.find()) {
            String palabra = matcher.group();
            if (StopWords.esPalabraClave(palabra)) {
                palabrasClave.add(palabra);
            }
        }

        return palabrasClave;
    }
}