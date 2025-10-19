package demo;

import java.util.ArrayList;

public class HojaPuntaje {
    private ArrayList<Categoria> categorias;

    public HojaPuntaje() {
        categorias = new ArrayList<>();
        // Inicializamos las 7 categorías del juego, cada una con 4 campos
        categorias.add(new Categoria("Fours"));
        categorias.add(new Categoria("Fives"));
        categorias.add(new Categoria("Sixes"));
        categorias.add(new Categoria("Straights"));
        categorias.add(new Categoria("Full House"));
        categorias.add(new Categoria("Choice"));
        categorias.add(new Categoria("Balut"));
    }

    // Muestra las categorías y puntajes actuales
    public void mostrarHoja() {
        for (Categoria cat : categorias) {
            System.out.println(cat.getNombre() + " : " + cat.getPuntaje() +
                    (cat.estaUsada() ? " (Usada)" : ""));
        }
    }
    public int getPuntaje(String nombreCategoria) {
        for (Categoria c : categorias) {
            if (c.getNombre().equalsIgnoreCase(nombreCategoria)) {
                return c.getPuntaje();
            }
        }
        return 0;
    }
    // Registra un puntaje en la categoría correspondiente
    public void registrarPuntaje(String nombreCategoria, int[] dados) {
        for (Categoria cat : categorias) {
            if (cat.getNombre().equalsIgnoreCase(nombreCategoria) && !cat.estaUsada()) {
                int puntaje = cat.calcularPuntaje(dados);
                cat.setPuntaje(puntaje);
                cat.marcarUsada();
                break;
            }
        }
    }

    // Devuelve true si todas las categorías ya fueron usadas
    public boolean todasUsadas() {
        for (Categoria cat : categorias) {
            if (!cat.estaUsada()) {
                return false;
            }
        }
        return true;
    }

    // Calcula el total de todos los puntajes
    public int calcularTotal() {
        int total = 0;
        for (Categoria cat : categorias) {
            total += cat.getPuntaje();
        }
        return total;
    }

    // Opcional: obtener categoría por nombre
    public Categoria getCategoria(String nombreCategoria) {
        for (Categoria cat : categorias) {
            if (cat.getNombre().equalsIgnoreCase(nombreCategoria)) {
                return cat;
            }
        }
        return null;
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

}
