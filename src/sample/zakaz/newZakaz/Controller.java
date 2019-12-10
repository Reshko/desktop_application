package sample.zakaz.newZakaz;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private Button newThink;

    @FXML
    private TextField nameField;

    @FXML
    private TextField numberField;

    @FXML
    void initialize() {

        exist.setOnAction(event -> {
            exist.getScene().getWindow().hide();
        });

        newThink.setOnAction(event -> {
            if(!nameField.getText().trim().equals("") && !numberField.getText().trim().equals("")){
                if(checkNameTovar(nameField.getText().trim())){
                    if(checkCountTovar(nameField.getText().trim(), numberField.getText().trim())){
                        addNewThink();
                        newThink.getScene().getWindow().hide();
                    }else errorCountData();
                }else errorName();
            }else{ error();}
        });
    }
    void addNewThink(){
        DatabaseHandler dbHandler = new DatabaseHandler();

        String name = nameField.getText();
        int num = Integer.parseInt(numberField.getText());
        TovarList tovarList = new TovarList();
        tovarList.setNameTovar(name);

        ResultSet res = dbHandler.selectPriceWhenAdd(tovarList);

        int price = 2;

        try {
            while (res.next()){
                price = res.getInt("price");
            }

            TovarList tovar = new TovarList(1,name,1,1,num,price);
            dbHandler.reqAddNewZakaz(tovar);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static boolean checkCountTovar(String strNameTovar,String countNumberTovar){
        DatabaseHandler dbHandler = new DatabaseHandler();
        TovarList tovarToZakaz = new TovarList();
        tovarToZakaz.setNameTovar(strNameTovar);
        ResultSet result = dbHandler.checkZakazCount(tovarToZakaz);

        int digit = 0;

        int idCountNumber = 0;

        int countNumber = 0;


        try {
            while (result.next()){
                countNumber = result.getInt("numberTovar");
                idCountNumber = result.getInt("idtovar2");

                if (countNumber < Integer.parseInt(countNumberTovar)) {
                    digit++;
                }else {
                    int shit = countNumber - Integer.parseInt(countNumberTovar);
                    TovarList tovarList = new TovarList(idCountNumber,"asd",0,0,shit,0);
                    dbHandler.changeCountWhenNewZakaz(tovarList);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        if (digit > 0){ return false; }
        else return true;
    }

    static boolean checkNameTovar(String strName){
        DatabaseHandler dbHandler = new DatabaseHandler();
        TovarList tovar = new TovarList();
        tovar.setNameTovar(strName);
        ResultSet result = dbHandler.checkZakaz(tovar);

        int count = 0;

        try {
            while(result.next()){
                count++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        if (count > 0) return true;
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

    void errorCountData(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/error/notFoundCountData.fxml"));
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

    void errorName(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/error/notFoundName.fxml"));
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
