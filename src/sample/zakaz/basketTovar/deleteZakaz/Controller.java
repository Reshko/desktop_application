package sample.zakaz.basketTovar.deleteZakaz;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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
import sample.zakaz.Tovar;
import sample.zakaz.basketTovar.ZakazTable;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        int idDel = Integer.parseInt(deleteField.getText().trim());


        DatabaseHandler db = new DatabaseHandler();
        ZakazTable zk = new ZakazTable();
        zk.setIdTable(idDel);
        ResultSet result = db.selectCountFromZakaz(zk);


        int countNumber = 0;
        String nameZakaz = "";
        try {
            while (result.next()){
                countNumber = result.getInt("numberZakaz");
                nameZakaz = result.getString("nameZakaz");
            }
            System.out.println(countNumber + " КОЛИЧЕСТВО");
            System.out.println(nameZakaz + " НАЗВАНИЕ");

            DatabaseHandler dbH = new DatabaseHandler();
            TovarList tovarList = new TovarList();
            tovarList.setNameTovar(nameZakaz);
            ResultSet res = dbH.selectCountFromTovar2(tovarList);

            int id = 0;
            int numbers = 0;

            try {
                while (res.next()){
                    id = res.getInt("idtovar2");
                    numbers = res.getInt("numberTovar");
                }
                System.out.println("id in tovar table  " + id);
                System.out.println("numbers in tovar table  " + numbers);

                int summ = numbers + countNumber;

                DatabaseHandler dbHa = new DatabaseHandler();
                TovarList tList = new TovarList(id,"",0,0,summ,0);
                dbHa.updateAfterDelete(tList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseHandler dbHandler = new DatabaseHandler();
        ZakazTable zkTable = new ZakazTable(idDel,"",0,0);
        dbHandler.reqDeleteZakazInBasket(zkTable);
    }

    public static boolean isNumeric(String x)
    {
        Pattern p = Pattern.compile("^\\d+(?:\\.\\d+)?$");
        Matcher m = p.matcher(x);
        return m.matches();
    }

    static boolean checkValues(String id){

        DatabaseHandler databaseHandler = new DatabaseHandler();
        ZakazTable zktable = new ZakazTable();

        int newId = Integer.parseInt(id);
        zktable.setIdTable(newId);


        ResultSet result = databaseHandler.checkIDfromZakaz(zktable);

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
