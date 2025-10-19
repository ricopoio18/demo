package demo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;

public class GUIBalut extends Application {
    private JuegoBalut juego;
    private Label lblTurno;
    private Label lblRonda;
    private Label lblResultado;
    private Button btnLanzar;
    private Button btnSiguiente;
    private HBox panelDados;
    private ArrayList<Label> etiquetasDados;
    private VBox panelCategorias;

    @Override
    public void start(Stage stage) {
        // Inicializa el juego con 2 jugadores
        juego = new JuegoBalut(Arrays.asList("Alice", "Bob"));

        lblTurno = new Label("Turno: " + juego.getJugadorActual().getNombre());
        lblRonda = new Label("Ronda: " + juego.getRondaActual());
        lblResultado = new Label("");

        // Panel de dados
        panelDados = new HBox(10);
        panelDados.setAlignment(Pos.CENTER);
        etiquetasDados = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Label dado = new Label("🎲");
            dado.setStyle("-fx-font-size: 40;");
            etiquetasDados.add(dado);
            panelDados.getChildren().add(dado);
        }

        btnLanzar = new Button("Lanzar dados");
        btnLanzar.setOnAction(e -> lanzar());

        // Panel de categorías
        panelCategorias = new VBox(5);
        panelCategorias.setAlignment(Pos.CENTER);
        for (Categoria cat : juego.getJugadorActual().getHojaPuntaje().getCategorias()) {
            Button botonCat = new Button(cat.getNombre());
            botonCat.setPrefWidth(150);
            botonCat.setOnAction(ev -> seleccionarCategoria(cat.getNombre()));
            panelCategorias.getChildren().add(botonCat);
        }

        btnSiguiente = new Button("Siguiente turno");
        btnSiguiente.setDisable(true);
        btnSiguiente.setOnAction(e -> siguienteTurno());

        VBox layout = new VBox(20, lblTurno, lblRonda, panelDados, btnLanzar, panelCategorias, lblResultado, btnSiguiente);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20; -fx-background-color: #e6f0ff;");

        Scene escena = new Scene(layout, 400, 600);
        stage.setTitle("Juego Balut");
        stage.setScene(escena);
        stage.show();
    }

    private void lanzar() {
        int[] valores = juego.lanzarDados();
        for (int i = 0; i < valores.length; i++) {
            etiquetasDados.get(i).setText("🎲" + valores[i]);
        }
        lblResultado.setText("Selecciona una categoría para registrar el puntaje.");
    }


    private void seleccionarCategoria(String nombre) {
        int[] valores = new int[5];
        for (int i = 0; i < etiquetasDados.size(); i++) {
            String texto = etiquetasDados.get(i).getText().replace("🎲", "");
            valores[i] = Integer.parseInt(texto);
        }

        Jugador actual = juego.getJugadorActual();
        actual.registrarPuntaje(nombre, valores);
        lblResultado.setText("Categoría '" + nombre + "' registrada con " + actual.getHojaPuntaje().getPuntaje(nombre) + " puntos.");

        btnLanzar.setDisable(true);
        btnSiguiente.setDisable(false);
    }

    private void siguienteTurno() {
        juego.siguienteTurno();

        if (juego.juegoTerminado()) {
            Jugador ganador = juego.determinarGanador();
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Juego terminado");
            alerta.setHeaderText("¡Ganador!");
            alerta.setContentText("Ganó " + ganador.getNombre() + " con " + ganador.getTotal() + " puntos.");
            alerta.showAndWait();
            System.exit(0);
        }

        lblTurno.setText("Turno: " + juego.getJugadorActual().getNombre());
        lblRonda.setText("Ronda: " + juego.getRondaActual());
        lblResultado.setText("");

        btnLanzar.setDisable(false);
        btnSiguiente.setDisable(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

