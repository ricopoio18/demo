package demo;

import java.util.Arrays;

public class Categoria {
    private String nombre;
    private boolean usada;
    private int puntaje;

    public Categoria(String nombre) {
        this.nombre = nombre;
        this.usada = false;
        this.puntaje = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean estaUsada() {
        return usada;
    }

    public void marcarUsada() {
        usada = true;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int p) {
        puntaje = p;
    }

    // Calcula el puntaje según el tipo de categoría y los valores de los dados
    public int calcularPuntaje(int[] dados) {
        int total = 0;
        switch (nombre.toLowerCase()) {
            case "fours":
                for (int d : dados) if (d == 4) total += 4;
                break;
            case "fives":
                for (int d : dados) if (d == 5) total += 5;
                break;
            case "sixes":
                for (int d : dados) if (d == 6) total += 6;
                break;
            case "straights":
                Arrays.sort(dados);
                boolean small = Arrays.equals(dados, new int[]{1,2,3,4,5});
                boolean large = Arrays.equals(dados, new int[]{2,3,4,5,6});
                total = small ? 15 : large ? 20 : 0;
                break;
            case "full house":
                Arrays.sort(dados);
                boolean triple = dados[0]==dados[2] || dados[1]==dados[3] || dados[2]==dados[4];
                boolean pair = (dados[0]==dados[1] && (dados[2]==dados[4] || dados[3]==dados[4])) || (dados[1]==dados[2] && dados[3]==dados[4]);
                total = (triple && pair) ? Arrays.stream(dados).sum() : 0;
                break;
            case "choice":
                for (int d : dados) total += d;
                break;
            case "balut":
                boolean allSame = Arrays.stream(dados).distinct().count() == 1;
                total = allSame ? Arrays.stream(dados).sum() + 20 : 0;
                break;
        }
        return total;
    }
}
