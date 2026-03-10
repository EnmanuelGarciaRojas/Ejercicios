import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {
    private ObservableList<Personaje> personajes = FXCollections.observableArrayList();
    private GridPane gridPersonajes;
    private int contadorFilas = 1; // Comienza en 1 porque la fila 0 es el encabezado

    @Override
    public void start(Stage stage) {
        // Crear contenedor principal
        VBox root = new VBox(15);
        root.setPadding(new Insets(15));

        // Sección del formulario
        VBox formulario = crearFormulario();
        
        // Sección de personajes registrados
        VBox seccionPersonajes = crearSeccionPersonajes();

        // Agregar secciones al contenedor principal
        root.getChildren().addAll(formulario, seccionPersonajes);

        // Crear la escena
        Scene scene = new Scene(root, 900, 700);
        
        // Configurar la ventana
        stage.setTitle("Registro de Personajes Dragon Ball Z");
        stage.setScene(scene);
        stage.show();
    }

    private VBox crearFormulario() {
        VBox formulario = new VBox(10);
        formulario.setStyle("-fx-border-color: #cccccc; -fx-padding: 15; -fx-border-radius: 5;");

        // Título
        Label titulo = new Label("REGISTRO DE PERSONAJES DRAGON BALL Z");
        titulo.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");

        // Campos del formulario
        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Ingrese el nombre del personaje");

        ComboBox<String> comboRaza = new ComboBox<>();
        comboRaza.setItems(FXCollections.observableArrayList(
            "Saiyajin", "Humano", "Namekiano", "Androide", "Majin", "Freezer Race"
        ));
        comboRaza.setPromptText("Seleccione una raza");

        TextField txtPoder = new TextField();
        txtPoder.setPromptText("Ingrese el nivel de poder (número)");

        TextField txtPlaneta = new TextField();
        txtPlaneta.setPromptText("Ingrese el planeta de origen");

        TextField txtTecnica = new TextField();
        txtTecnica.setPromptText("Ingrese la técnica especial");

        TextField txtEdad = new TextField();
        txtEdad.setPromptText("Ingrese la edad (número)");

        // Crear el GridPane para las etiquetas y campos
        GridPane gridFormulario = new GridPane();
        gridFormulario.setHgap(10);
        gridFormulario.setVgap(10);

        gridFormulario.add(new Label("Nombre:"), 0, 0);
        gridFormulario.add(txtNombre, 1, 0);

        gridFormulario.add(new Label("Raza:"), 0, 1);
        gridFormulario.add(comboRaza, 1, 1);

        gridFormulario.add(new Label("Nivel de Poder:"), 0, 2);
        gridFormulario.add(txtPoder, 1, 2);

        gridFormulario.add(new Label("Planeta de Origen:"), 0, 3);
        gridFormulario.add(txtPlaneta, 1, 3);

        gridFormulario.add(new Label("Técnica Especial:"), 0, 4);
        gridFormulario.add(txtTecnica, 1, 4);

        gridFormulario.add(new Label("Edad:"), 0, 5);
        gridFormulario.add(txtEdad, 1, 5);

        // Botón Agregar
        Button btnAgregar = new Button("Agregar");
        btnAgregar.setStyle("-fx-font-size: 12; -fx-padding: 8 30;");
        btnAgregar.setOnAction(e -> agregarPersonaje(
            txtNombre, comboRaza, txtPoder, txtPlaneta, txtTecnica, txtEdad
        ));

        // Contenedor para el botón
        HBox hboxBoton = new HBox();
        hboxBoton.getChildren().add(btnAgregar);

        // Agregar al formulario
        formulario.getChildren().addAll(titulo, gridFormulario, hboxBoton);

        return formulario;
    }

    private VBox crearSeccionPersonajes() {
        VBox seccion = new VBox(10);
        seccion.setStyle("-fx-border-color: #cccccc; -fx-padding: 15; -fx-border-radius: 5;");

        // Título
        Label titulo = new Label("PERSONAJES REGISTRADOS");
        titulo.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");

        // GridPane para mostrar personajes
        gridPersonajes = new GridPane();
        gridPersonajes.setHgap(15);
        gridPersonajes.setVgap(8);
        gridPersonajes.setPadding(new Insets(10));
        gridPersonajes.setStyle("-fx-font-family: monospace;");

        // Crear encabezados
        crearEncabezados();

        // ScrollPane para permitir scroll si hay muchos personajes
        ScrollPane scrollPane = new ScrollPane(gridPersonajes);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(400);

        seccion.getChildren().addAll(titulo, scrollPane);

        return seccion;
    }

    private void crearEncabezados() {
        String[] encabezados = {"Nombre", "Raza", "Poder", "Planeta", "Técnica", "Edad"};
        for (int i = 0; i < encabezados.length; i++) {
            Label encabezado = new Label(encabezados[i]);
            encabezado.setStyle("-fx-font-weight: bold; -fx-text-fill: #0066cc;");
            gridPersonajes.add(encabezado, i, 0);
        }
    }

    private void agregarPersonaje(TextField txtNombre, ComboBox<String> comboRaza,
                                  TextField txtPoder, TextField txtPlaneta,
                                  TextField txtTecnica, TextField txtEdad) {
        try {
            // Validar campos no vacíos
            if (txtNombre.getText().trim().isEmpty()) {
                mostrarError("El nombre no puede estar vacío");
                return;
            }

            if (comboRaza.getValue() == null) {
                mostrarError("Debe seleccionar una raza");
                return;
            }

            if (txtPoder.getText().trim().isEmpty()) {
                mostrarError("El nivel de poder no puede estar vacío");
                return;
            }

            if (txtPlaneta.getText().trim().isEmpty()) {
                mostrarError("El planeta de origen no puede estar vacío");
                return;
            }

            if (txtTecnica.getText().trim().isEmpty()) {
                mostrarError("La técnica especial no puede estar vacía");
                return;
            }

            if (txtEdad.getText().trim().isEmpty()) {
                mostrarError("La edad no puede estar vacía");
                return;
            }

            // Validar que poder sea un número mayor que 0
            int poder;
            try {
                poder = Integer.parseInt(txtPoder.getText());
                if (poder <= 0) {
                    mostrarError("El nivel de poder debe ser mayor que 0");
                    return;
                }
            } catch (NumberFormatException ex) {
                mostrarError("El nivel de poder debe ser un número válido");
                return;
            }

            // Validar que edad sea un número mayor que 0
            int edad;
            try {
                edad = Integer.parseInt(txtEdad.getText());
                if (edad <= 0) {
                    mostrarError("La edad debe ser mayor que 0");
                    return;
                }
            } catch (NumberFormatException ex) {
                mostrarError("La edad debe ser un número válido");
                return;
            }

            // Crear nuevo personaje
            Personaje nuevoPersonaje = new Personaje(
                txtNombre.getText(),
                comboRaza.getValue(),
                poder,
                txtPlaneta.getText(),
                txtTecnica.getText(),
                edad
            );

            // Agregar a la lista
            personajes.add(nuevoPersonaje);

            // Agregar al GridPane
            agregarFilaAlGrid(nuevoPersonaje, contadorFilas);
            contadorFilas++;

            // Limpiar formulario
            limpiarFormulario(txtNombre, comboRaza, txtPoder, txtPlaneta, txtTecnica, txtEdad);

            mostrarExito("Personaje agregado correctamente");

        } catch (Exception e) {
            mostrarError("Error al agregar personaje: " + e.getMessage());
        }
    }

    private void agregarFilaAlGrid(Personaje personaje, int fila) {
        gridPersonajes.add(new Label(personaje.getNombre()), 0, fila);
        gridPersonajes.add(new Label(personaje.getRaza()), 1, fila);
        gridPersonajes.add(new Label(String.valueOf(personaje.getNivelPoder())), 2, fila);
        gridPersonajes.add(new Label(personaje.getPlanetaOrigen()), 3, fila);
        gridPersonajes.add(new Label(personaje.getTecnicaEspecial()), 4, fila);
        gridPersonajes.add(new Label(String.valueOf(personaje.getEdad())), 5, fila);
    }

    private void limpiarFormulario(TextField txtNombre, ComboBox<String> comboRaza,
                                   TextField txtPoder, TextField txtPlaneta,
                                   TextField txtTecnica, TextField txtEdad) {
        txtNombre.clear();
        comboRaza.setValue(null);
        txtPoder.clear();
        txtPlaneta.clear();
        txtTecnica.clear();
        txtEdad.clear();
        txtNombre.requestFocus();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error en la validación");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarExito(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
