package demo;
import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private HojaPuntaje hojaPuntaje;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.hojaPuntaje = new HojaPuntaje(); // Cada jugador tiene su propia hoja
    }

    // Devuelve el nombre del jugador
    public String getNombre() {
        return nombre;
    }

    // Devuelve la hoja de puntaje del jugador
    public HojaPuntaje getHojaPuntaje() {
        return hojaPuntaje;
    }

    // Registra un puntaje en una categoría
    public void registrarPuntaje(String nombreCategoria, int[] dados) {
        hojaPuntaje.registrarPuntaje(nombreCategoria, dados);
    }

    // Calcula el total acumulado del jugador
    public int getTotal() {
        return hojaPuntaje.calcularTotal();
    }
}
