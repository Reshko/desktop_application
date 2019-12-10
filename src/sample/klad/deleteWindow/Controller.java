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
import sample.klad.Textile;

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
    private TextField tableName;

    @FXML
    void initialize() {
        deleteReq.setOnAction(event -> {
            if(!deleteField.getText().trim().equals("") && !tableName.getText().trim().equals("")){
                if(Integer.parseInt(deleteField.getText()) > 0){
                    if(tableName.getText().equals("Ткани")){
                        secondDel();
                        deleteReq.getScene().getWindow().hide();
                    } else if (tableName.getText().equals("Фурнитура")){
                        firstDel();
                        deleteReq.getScene().getWindow().hide();
                    }
                    else error();
                } else error();
            } else error();
        });
        exist.setOnAction(event -> {
            exist.getScene().getWindow().hide();
        });
    }

    public void firstDel(){
        DatabaseHandler db = new DatabaseHandler();
        int id = Integer.parseInt(deleteField.getText().trim());
        TovarList tovar = new TovarList(id,"",1,1,1,1);
        db.reqDeleteTovar(tovar);

    }

    public void secondDel(){
        DatabaseHandler db = new DatabaseHandler();
        int id = Integer.parseInt(deleteField.getText().trim());
        Textile textile = new Textile(id,"","","",1,1);
        db.reqDeleteTextile(textile);
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
