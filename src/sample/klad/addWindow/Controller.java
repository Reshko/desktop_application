package sample.klad.addWindow;

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
    private Button addReq;

    @FXML
    private TextField idName;

    @FXML
    private TextField idWidth;

    @FXML
    private TextField idLong;

    @FXML
    private TextField idNumber;

    @FXML
    void initialize() {
        exist.setOnAction(event -> {
            exist.getScene().getWindow().hide();
        });
        addReq.setOnAction(event -> {
            if(!idName.getText().trim().equals("") && !idWidth.getText().trim().equals("") && !idLong.getText().trim().equals("") && !idNumber.getText().trim().equals("")){
                upNewTovar();
                addReq.getScene().getWindow().hide();
            }else {
                error();
            }
        });

    }



    private void upNewTovar() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String name = idName.getText();
        String  width = idWidth.getText();
        String longer = idLong.getText();
        String number = idNumber.getText();

        int w = Integer.parseInt(width);
        int l = Integer.parseInt(longer);
        int n = Integer.parseInt(number);

        //User user = new User(type,login,password);
        TovarList tovar = new TovarList(1,name,w,l,n);

        dbHandler.reqAddNewTovar(tovar);
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
