package sample.help;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label label_id;

    @FXML
    private Button allOk;

    @FXML
    void initialize() {
        allOk.setOnAction(event -> {
            allOk.getScene().getWindow().hide();
        });
    }
}
