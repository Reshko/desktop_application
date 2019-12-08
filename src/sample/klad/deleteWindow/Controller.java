package sample.klad.deleteWindow;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.connectToDb.DatabaseHandler;
import sample.connectToDb.TovarList;

import java.io.IOException;
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
    private Button exist;

    @FXML
    private Button deleteReq;

    @FXML
    private TextField deleteField;

    @FXML
    void initialize() {
        deleteReq.setOnAction(event -> {
            if(!deleteField.getText().trim().equals("")){
                delValue();
                deleteReq.getScene().getWindow().hide();
            }
            else error();
        });
        exist.setOnAction(event -> {
            exist.getScene().getWindow().hide();
        });
    }

    private void delValue(){
        DatabaseHandler dbHandler = new DatabaseHandler();
        int idDel = Integer.parseInt(deleteField.getText().trim());
        TovarList tovar = new TovarList(idDel,"",1,1,1);
        dbHandler.reqDeleteTovar(tovar);
    }
    void error(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/error/app.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent parent = loader.getRoot();
        Stage stage =  new Stage();
        stage.setTitle("Заполните данные");
        stage.setScene(new Scene(parent));
        stage.showAndWait();
    }
}
