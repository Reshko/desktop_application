package sample.meneger.changeWindow;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Handler;
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
import sample.connectToDb.User;
import sample.meneger.ControllerMenager;

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
    private TextField idPrice;

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
            if(!idField.getText().trim().equals("") &&!idName.getText().trim().equals("") && !idWidth.getText().trim().equals("") && !idLong.getText().trim().equals("") && !idNumber.getText().trim().equals("") && !idPrice.getText().trim().equals("")){
                if(isNumeric(idField.getText()) && isNumeric(idWidth.getText()) && isNumeric(idLong.getText()) && isNumeric(idNumber.getText()) && isNumeric(idPrice.getText())){
                    if(checkValues(idField.getText())) {
                        if (isAlpha(idName.getText().trim())) {
                            if (Integer.parseInt(idField.getText()) > 0 && Integer.parseInt(idWidth.getText()) > 0 && Integer.parseInt(idLong.getText()) > 0 && Integer.parseInt(idNumber.getText()) > 0 && Integer.parseInt(idPrice.getText()) > 0) {
                                changeValue();
                                changeReq.getScene().getWindow().hide();
                            } else errorMinus();
                        } else errorABC();
                    }else notFoundId();
                }else errorDigit();
            }else error();

        });
    }

    void changeValue(){
        DatabaseHandler databaseHandler = new DatabaseHandler();
        int idVal = Integer.parseInt(idField.getText());
        String name = idName.getText();
        int width = Integer.parseInt(idWidth.getText());
        int longer = Integer.parseInt(idLong.getText());
        int number = Integer.parseInt(idNumber.getText());
        int price = Integer.parseInt(idPrice.getText());

        TovarList tovarList = new TovarList(idVal,name,width,longer,number,price);
        databaseHandler.reqChangeTovar(tovarList);
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
