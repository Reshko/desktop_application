package sample.zakaz.constructor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    private Button add;

    @FXML
    private TextField width;

    @FXML
    private TextField hight;

    @FXML
    private TextField variant;

    @FXML
    private TextField okontovka;

    @FXML
    private TextField furnit;

    @FXML
    private TextField size;

    @FXML
    private Button look;


    @FXML
    void initialize() {

        exist.setOnAction(event -> {
            exist();
        });

        add.setOnAction(event -> {
            upNewTovar();
        });

        look.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/zakaz/constructor/tableConstr/app.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent parent = loader.getRoot();
            Stage stage =  new Stage();
            stage.setTitle("Окно директора");
            stage.setScene(new Scene(parent));
            stage.show();


        });

    }

    private void upNewTovar() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        int widht = Integer.parseInt(width.getText().trim());
        int hightе = Integer.parseInt(hight.getText().trim());
        int variante = Integer.parseInt(variant.getText().trim());
        int okontovkae = Integer.parseInt(okontovka.getText().trim());
        int furnite = Integer.parseInt(furnit.getText().trim());
        int sizee = Integer.parseInt(size.getText().trim());


        Constructor constructor = new Constructor(widht,hightе,variante,okontovkae,furnite,sizee);

        dbHandler.reqAddNewConstr(constructor);
    }

    void exist(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../app.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent parent = loader.getRoot();
        Stage stage =  new Stage();
        stage.setTitle("Окно авторизации");
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
