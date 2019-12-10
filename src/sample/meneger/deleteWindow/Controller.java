package sample.meneger.deleteWindow;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private Button deleteReq;

    @FXML
    private TextField deleteField;

    @FXML
    void initialize() {
        deleteReq.setOnAction(event -> {
            if(!deleteField.getText().trim().equals("")){
                if(isNumeric(deleteField.getText())){
                    if(checkValues(deleteField.getText())){
                        delValue();
                        deleteReq.getScene().getWindow().hide();
                    }else notFoundId();
                }else errorABC();
            } else error();
        });
        exist.setOnAction(event -> {
            exist.getScene().getWindow().hide();
        });
    }

    private void delValue(){
        DatabaseHandler dbHandler = new DatabaseHandler();
        int idDel = Integer.parseInt(deleteField.getText().trim());
        TovarList tovar = new TovarList(idDel,"",1,1,1,1);
        dbHandler.reqDeleteTovar(tovar);
    }


    public static boolean isNumeric(String x)
    {
        Pattern p = Pattern.compile("^\\d+(?:\\.\\d+)?$");
        Matcher m = p.matcher(x);
        return m.matches();
    }

    static boolean checkValues(String id){

        DatabaseHandler databaseHandler = new DatabaseHandler();
        TovarList tovar = new TovarList();

        int newId = Integer.parseInt(id);
        tovar.setIdTovar(newId);
        ResultSet result = databaseHandler.checkID(tovar);


        int counter = 0;

        try {
            while(result.next()){
                counter++;

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        if (counter > 0) return true;
        else return false;
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

    void errorABC(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/error/falseDate.fxml"));
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

    void notFoundId(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/error/notFoundId.fxml"));
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
