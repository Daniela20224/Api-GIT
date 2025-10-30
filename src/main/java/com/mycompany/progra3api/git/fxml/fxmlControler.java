package com.mycompany.progra3api.git.fxml;

import com.mycompany.progra3api.git.domain.repositorio;
import com.mycompany.progra3api.git.domain.usuario;
import com.mycompany.progra3api.git.service.GitHApi;
import com.mycompany.progra3api.git.util.formateador;
import com.mycompany.progra3api.git.util.LUtil;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.Map;

public class fxmlControler {

    // Campos del formulario
    @FXML private TextField txtUsername;
    @FXML private Button btnBuscar;
    @FXML private Label lblStatus;

    // Información del perfil
    @FXML private ImageView imgAvatar;
    @FXML private Label lblName;
    @FXML private Label lblBio;
    @FXML private Label lblLocation;
    @FXML private Label lblFollowers;
    @FXML private Label lblCreated;

    // Tabla de repositorios
    @FXML private TableView<repositorio> tblRepos;
    @FXML private TableColumn<repositorio, String> colName;
    @FXML private TableColumn<repositorio, String> colDesc;

    // Gráfico de lenguajes
    @FXML private PieChart chartLanguages;

    private final GitHApi apiService = new GitHApi();

    @FXML
    private void initialize() {
        // Configurar columnas de la tabla
        colName.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(formateador.safe(c.getValue().getName())));
        colDesc.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(formateador.safe(c.getValue().getLanguage())));

        // Ajustar propiedades del gráfico circular
        chartLanguages.setTitle("Distribución de lenguajes");
        chartLanguages.setLegendVisible(true);
        chartLanguages.setLabelsVisible(true);
        chartLanguages.setMinSize(400, 300);
    }

    @FXML
    private void onBuscar() {
        String userInput = txtUsername.getText().trim();

        if (userInput.isEmpty()) {
            lblStatus.setText("Por favor escriba un nombre de usuario válido");
            lblStatus.setStyle("-fx-text-fill: crimson;");
            return;
        }

        lblStatus.setText("Conectando con GitHub...");
        lblStatus.setStyle("-fx-text-fill: black;");

        new Thread(() -> {
            try {
                usuario usuario = apiService.getUser(userInput);
                List<repositorio> listaRepos = apiService.getRepos(userInput);

                Platform.runLater(() -> actualizarVista(usuario, listaRepos));
            } catch (Exception e) {
                Platform.runLater(() -> {
                    lblStatus.setText("No se pudo obtener la información: " + e.getMessage());
                    lblStatus.setStyle("-fx-text-fill: red;");
                });
            }
        }).start();
    }

    private void actualizarVista(usuario usuario, List<repositorio> repositorios) {
        lblStatus.setText("Búsqueda completada (" + repositorios.size() + " repositorios encontrados)");
        lblStatus.setStyle("-fx-text-fill: green;");

        lblName.setText(formateador.safe(usuario.getLogin()));
        lblBio.setText(formateador.safe(usuario.getBio()));
        lblLocation.setText("Ubicación: " + formateador.safe(usuario.getLocation()));
        lblFollowers.setText("Seguidores: " + usuario.getFollowers());
        lblCreated.setText("Cuenta creada: " + formateador.formatDate(usuario.getCreatedAt()));

        // Imagen del avatar
        if (usuario.getAvatarUrl() != null && !usuario.getAvatarUrl().isBlank()) {
            imgAvatar.setImage(new Image(usuario.getAvatarUrl(), true));
        } else {
            imgAvatar.setImage(null);
        }

        // Actualizar tabla
        ObservableList<repositorio> datosTabla = FXCollections.observableArrayList(repositorios);
        tblRepos.setItems(datosTabla);

        // Actualizar gráfico
        actualizarGrafico(repositorios);
    }

    private void actualizarGrafico(List<repositorio> repositorios) {
        try {
            chartLanguages.getData().clear();
            Map<String, Double> datos = LUtil.calcularPorcentajesLenguajes(repositorios);

            if (datos.isEmpty()) {
                chartLanguages.setTitle("Sin información de lenguajes");
                chartLanguages.getData().add(new PieChart.Data("Sin datos", 100));
                return;
            }

            ObservableList<PieChart.Data> datosGrafico = FXCollections.observableArrayList();
            for (var entry : datos.entrySet()) {
                String etiqueta = String.format("%s (%.1f%%)", entry.getKey(), entry.getValue());
                datosGrafico.add(new PieChart.Data(etiqueta, entry.getValue()));
            }

            chartLanguages.setData(datosGrafico);
            chartLanguages.setTitle("Lenguajes usados (" + repositorios.size() + ")");

            Platform.runLater(chartLanguages::requestLayout);
        } catch (Exception e) {
            chartLanguages.setTitle("Error al cargar el gráfico");
            System.err.println("Fallo al generar el gráfico: " + e.getMessage());
        }
    }
}
