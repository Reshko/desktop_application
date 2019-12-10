package sample.meneger.addWindow;

import java.io.IOException;
import java.net.URL;
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
    private TextField idPrice;

    @FXML
    void initialize() {
        exist.setOnAction(event -> {
            exist.getScene().getWindow().hide();
        });
        addReq.setOnAction(event -> {
            if(!idName.getText().trim().equals("") && !idWidth.getText().trim().equals("") && !idLong.getText().trim().equals("") && !idNumber.getText().trim().equals("") && !idPrice.getText().trim().equals("")){
                if(isNumeric(idWidth.getText()) && isNumeric(idLong.getText()) && isNumeric(idNumber.getText()) && isNumeric(idPrice.getText())){
                    if(isAlpha(idName.getText().trim())){
                        if(Integer.parseInt(idWidth.getText()) > 0 && Integer.parseInt(idLong.getText()) > 0 && Integer.parseInt(idNumber.getText()) > 0  && Integer.parseInt(idPrice.getText()) > 0){
                            upNewTovar();
                            addReq.getScene().getWindow().hide();
                        }else errorMinus();
                    }else errorABC();
                }else errorDigit();
            }else error();
        });
    }



            //Integer.parseInt(idWidth.getText()) > 0 && Integer.parseInt(idLong.getText()) > 0 && Integer.parseInt(idNumber.getText()) > 0  && Integer.parseInt(idPrice.getText()) > 0



    private void upNewTovar() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String name = idName.getText();
        String  width = idWidth.getText();
        String longer = idLong.getText();
        String number = idNumber.getText();
        String price = idPrice.getText();

        int w = Integer.parseInt(width);
        int l = Integer.parseInt(longer);
        int n = Integer.parseInt(number);
        int p = Integer.parseInt(price);

        //User user = new User(type,login,password);
        TovarList tovar = new TovarList(1,name,w,l,n,p);

        dbHandler.reqAddNewTovar(tovar);
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

}
