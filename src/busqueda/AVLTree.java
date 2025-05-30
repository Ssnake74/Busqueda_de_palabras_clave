package busqueda;

import java.util.*;

public class AVLTree {
    private class Nodo {
        String palabra;
        Set<String> documentos = new HashSet<>();
        Nodo izquierda, derecha;
        int altura;

        Nodo(String palabra, String documento) {
            this.palabra = palabra;
            this.documentos.add(documento);
            this.altura = 1;
        }
    }

    private Nodo raiz;
    private int comparaciones;
    private int totalPalabras;
    private int busquedas;
    private long tiempoTotal;

    public void insertar(String palabra, String documento) {
        raiz = insertar(raiz, palabra, documento);
        totalPalabras++;
    }

    private Nodo insertar(Nodo nodo, String palabra, String documento) {
        if (nodo == null) return new Nodo(palabra, documento);

        if (palabra.compareTo(nodo.palabra) < 0) {
            nodo.izquierda = insertar(nodo.izquierda, palabra, documento);
        } else if (palabra.compareTo(nodo.palabra) > 0) {
            nodo.derecha = insertar(nodo.derecha, palabra, documento);
        } else {
            nodo.documentos.add(documento);
            return nodo;
        }

        nodo.altura = 1 + Math.max(altura(nodo.izquierda), altura(nodo.derecha));
        return balancear(nodo);
    }

    private int altura(Nodo nodo) {
        return nodo == null ? 0 : nodo.altura;
    }

    private int factorBalance(Nodo nodo) {
        return altura(nodo.izquierda) - altura(nodo.derecha);
    }

    private Nodo rotarDerecha(Nodo y) {
        Nodo x = y.izquierda;
        Nodo T2 = x.derecha;

        x.derecha = y;
        y.izquierda = T2;

        y.altura = Math.max(altura(y.izquierda), altura(y.derecha)) + 1;
        x.altura = Math.max(altura(x.izquierda), altura(x.derecha)) + 1;

        return x;
    }

    private Nodo rotarIzquierda(Nodo x) {
        Nodo y = x.derecha;
        Nodo T2 = y.izquierda;

        y.izquierda = x;
        x.derecha = T2;

        x.altura = Math.max(altura(x.izquierda), altura(x.derecha)) + 1;
        y.altura = Math.max(altura(y.izquierda), altura(y.derecha)) + 1;

        return y;
    }

    private Nodo balancear(Nodo nodo) {
        int balance = factorBalance(nodo);

        if (balance > 1) {
            if (factorBalance(nodo.izquierda) < 0) {
                nodo.izquierda = rotarIzquierda(nodo.izquierda);
            }
            return rotarDerecha(nodo);
        }

        if (balance < -1) {
            if (factorBalance(nodo.derecha) > 0) {
                nodo.derecha = rotarDerecha(nodo.derecha);
            }
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    public Set<String> buscar(String palabra) {
        comparaciones = 0;
        long inicio = System.nanoTime();
        Set<String> resultado = buscar(raiz, palabra.toLowerCase());
        long fin = System.nanoTime();
        busquedas++;
        tiempoTotal += (fin - inicio);
        return resultado;
    }

    private Set<String> buscar(Nodo nodo, String palabra) {
        if (nodo == null) return new HashSet<>();
        comparaciones++;

        if (palabra.equals(nodo.palabra)) {
            return nodo.documentos;
        } else if (palabra.compareTo(nodo.palabra) < 0) {
            return buscar(nodo.izquierda, palabra);
        } else {
            return buscar(nodo.derecha, palabra);
        }
    }

    public int getComparaciones() {
        return comparaciones;
    }

    public int getTotalPalabras() {
        return totalPalabras;
    }

    public int getTotalBusquedas() {
        return busquedas;
    }

    public double getTiempoPromedioBusqueda() {
        return busquedas == 0 ? 0 : tiempoTotal / (double) busquedas / 1_000_000.0; 
    }
}