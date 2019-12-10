package sample.klad.changeWindow;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            if(!idField.getText().trim().equals("")&&!idName.getText().trim().equals("")&&!idWidth.getText().trim().equals("")&&!idLong.getText().trim().equals("")){
                if(idName.getText().trim().equals("Фурнитура")){
                    changeOne();
                    changeReq.getScene().getWindow().hide();
                } else if(idName.getText().trim().equals("Ткани")){
                    changeTwo();
                    changeReq.getScene().getWindow().hide();
                }else error();
            }else error();
        });
    }

    void changeOne(){
        DatabaseHandler db = new DatabaseHandler();
        int id = Integer.parseInt(idField.getText());
        int width = Integer.parseInt(idWidth.getText());
        int longer = Integer.parseInt(idLong.getText());
        TovarList tovarList = new TovarList(id,"",width,longer,1,1);
        db.reqChangeTovar(tovarList);
    }

    void changeTwo(){
        DatabaseHandler db = new DatabaseHandler();
        String name = idName.getText();
        int id = Integer.parseInt(idField.getText());
        int width = Integer.parseInt(idWidth.getText());
        int longer = Integer.parseInt(idLong.getText());
        Textile textile = new Textile(id,"","","",longer,width);
        db.reqChangeTextile(textile);
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

    public boolean isAlpha(String name) {
        return name.matches("[а-яА-Я]+");
    }

    public static boolean isNumeric(String x)
    {
        Pattern p = Pattern.compile("^\\d+(?:\\.\\d+)?$");
        Matcher m = p.matcher(x);
        return m.matches();
    }

    void errorMinus(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/error/minusData.fxml"));
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

    void errorDigit(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/error/digitData.fxml"));
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
