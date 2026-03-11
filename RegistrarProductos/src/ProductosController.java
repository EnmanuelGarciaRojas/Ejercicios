import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductosController {
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtCantidad;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TableView<Productos> tabla;
    @FXML
    private TableColumn<Productos, String> colNombre;
    @FXML
    private TableColumn<Productos, Double> colPrecio;
    @FXML
    private TableColumn<Productos, Integer> colCantidad;
    @FXML
    private Label lblError;

    private ObservableList<Productos> productos;

    @FXML
    public void initialize() {
        productos = FXCollections.observableArrayList();

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        colPrecio.setCellFactory(col -> new javafx.scene.control.TableCell<>() {
            @Override
            protected void updateItem(Double valor, boolean empty) {
                super.updateItem(valor, empty);
                setText(empty || valor == null ? null : String.format("%.2f", valor));
            }
        });

        tabla.setItems(productos);

        btnAgregar.setOnAction(e -> agregarProducto());
        btnEliminar.setOnAction(e -> eliminarProducto());

        lblError.setText("");
    }

    private void agregarProducto() {
        lblError.setText(""); 

        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            lblError.setText("Error: El nombre no puede estar vacío");
            return;
        }

        try {
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());

            if (precio < 0 || cantidad < 0) {
                lblError.setText("Error: Precio y cantidad deben ser valores positivos");
                return;
            }

            Productos nuevoProducto = new Productos(nombre, precio, cantidad);
            productos.add(nuevoProducto);

            txtNombre.clear();
            txtPrecio.clear();
            txtCantidad.clear();

        } catch (NumberFormatException ex) {
            lblError.setText("Error: Precio y cantidad deben ser números válidos");
        }
    }

    private void eliminarProducto() {
        lblError.setText("");

        Productos seleccionado = tabla.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            productos.remove(seleccionado);
        } else {
            lblError.setText("Error: Debe seleccionar un producto para eliminar");
        }
    }
}