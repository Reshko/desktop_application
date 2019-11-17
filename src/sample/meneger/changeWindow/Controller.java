package sample.meneger.changeWindow;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Handler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private TextField idName;

    @FXML
    private TextField idWidth;

    @FXML
    private TextField idLong;

    @FXML
    private TextField idNumber;

    @FXML
    private Button changeReq;

    @FXML
    private TextField idField;

    @FXML
    void initialize() {
        exist.setOnAction(event -> {
            exist.getScene().getWindow().hide();
        });
        changeReq.setOnAction(event -> {
            if(!idField.getText().trim().equals("")&&!idName.getText().trim().equals("")&&!idWidth.getText().trim().equals("")&&!idLong.getText().trim().equals("")&&!idNumber.getText().trim().equals("")){
                changeValue();
            }else System.out.println("ERROR");

        });
    }
    void changeValue(){
        DatabaseHandler databaseHandler = new DatabaseHandler();
        int idVal = Integer.parseInt(idField.getText());
        String name = idName.getText();
        int width = Integer.parseInt(idWidth.getText());
        int longer = Integer.parseInt(idLong.getText());
        int number = Integer.parseInt(idNumber.getText());

        TovarList tovarList = new TovarList(idVal,name,width,longer,number);

        databaseHandler.reqChangeTovar(tovarList);

    }
}
