package busqueda;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StopWords {
    private static final Set<String> stopWords = new HashSet<>(Arrays.asList(
        "el", "la", "los", "las", "de", "y", "a", "en", "un", "una", "que",
        "por", "con", "no", "es", "para", "al", "lo", "como", "más", "o",
        "su", "pero", "sus", "le", "ya", "si", "porque", "esta", "entre",
        "cuando", "muy", "sin", "sobre"
    ));

    public static boolean esPalabraClave(String palabra) {
        return !stopWords.contains(palabra.toLowerCase());
    }
}