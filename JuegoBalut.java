package demo;

import java.util.ArrayList;
import java.util.List;

public class JuegoBalut {
    private ArrayList<Jugador> jugadores;
    private Dado[] dados;
    private int rondaActual;
    private int turnoActual;

    public JuegoBalut(List<String> nombresJugadores) {
        jugadores = new ArrayList<>();
        for (String nombre : nombresJugadores) {
            jugadores.add(new Jugador(nombre));
        }
        dados = new Dado[5];
        for (int i = 0; i < 5; i++) {
            dados[i] = new Dado();
        }
        rondaActual = 1;
        turnoActual = 0;
    }

    public void iniciarRonda() {
        // Resetea los dados al inicio de la ronda
        for (Dado d : dados) d.setApartado(false);
    }

    public int[] lanzarDados() {
        int[] valores = new int[5];
        for (int i = 0; i < dados.length; i++) {
            valores[i] = dados[i].lanzar();
        }
        return valores;
    }

    public Jugador getJugadorActual() {
        return jugadores.get(turnoActual);
    }

    public void siguienteTurno() {
        turnoActual = (turnoActual + 1) % jugadores.size();
        if (turnoActual == 0) rondaActual++;
    }

    public boolean juegoTerminado() {
        for (Jugador j : jugadores) {
            if (!j.getHojaPuntaje().todasUsadas()) return false;
        }
        return true;
    }

    public Jugador determinarGanador() {
        Jugador ganador = jugadores.get(0);
        for (Jugador j : jugadores) {
            if (j.getTotal() > ganador.getTotal()) ganador = j;
        }
        return ganador;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public int getRondaActual() {
        return rondaActual;
    }
}

