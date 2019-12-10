package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.connectToDb.DatabaseHandler;
import sample.connectToDb.User;

public class Two {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField typeField;

    @FXML
    private TextField logField;

    @FXML
    private PasswordField passField;

    @FXML
    private Button enterButton;

    @FXML
    private Button backBtn;

    @FXML
    private Button lookPassword;

    @FXML
    private Label idLabel;

    @FXML
    private ChoiceBox<String> chBox;

    @FXML
    private Button help;


    @FXML
    void initialize() {


        enterButton.setOnAction(event -> {
            System.out.println(chBox.getValue());
            String typeText = typeField.getText().trim();
            String loginText = logField.getText().trim();
            String passwordText = passField.getText().trim();

            if(!typeText.equals("") && !loginText.equals("") && !passwordText.equals("")){
                if (passwordText.length() >= 6){

                    char [] pass  = passwordText.toCharArray();
                    System.out.println(pass.toString());

                    if(digits(pass) && words(pass) && other(pass) && checkValues(loginText)){
                        signUpNewUser();
                    }else error();
                }else error();
            }else error();
        });

        help.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/help/app.fxml"));

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
        });



        backBtn.setOnAction(event -> {
            backBtn.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/sample.fxml"));

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
        });

        lookPassword.setOnAction(event -> {
            //System.out.println(passField.getText());
            idLabel.setText(passField.getText());
        });
    }

    private void signUpNewUser() {

        DatabaseHandler dbHandler = new DatabaseHandler();

        String type = typeField.getText();
        String login = logField.getText();
        String password = passField.getText();

        User user = new User(type,login,password);

        dbHandler.sighUpUser(user);
    }

    static boolean digits(char[] asd){
        char [] array = new char[]{'1','2','3','4','5','6','7','8','9'};
        int count = 0;
        for(int i = 0; i < asd.length;i++){
            for(int j = 0;j<array.length;j++){
                if(asd[i] == array[j]){
                    count++;
                }
            }
        }
        if(count > 0){
            return true;
        }else{
            return false;
        }
    }

    static boolean other(char[] asd){
        char [] oth = new char[]{'@','!','#','$','%','^'};
        int count = 0;
        for(int i = 0; i < asd.length;i++){
            for(int j = 0;j<oth.length;j++){
                if(asd[i] == oth[j]){
                    count++;
                }
            }
        }
        if(count > 0){
            return true;
        }else{
            return false;
        }
    }

    static boolean checkValues(String strLogin){
        DatabaseHandler databaseHandler = new DatabaseHandler();
        User user = new User();
        user.setLoginUser(strLogin);
        ResultSet result = databaseHandler.checkLogin(user);


        int counter = 0;

        try {
            while(result.next()){
                counter++;

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        if (counter > 0) return false;
        else return true;


    }

    static boolean words(char[] asd){
/*
        char[] alf = {'a','b','c','d','e','f','g','h','i','j','k','l',
                'm','n','o','p','q','r','s','t','u','v','w','x','y','z'};
*/
        char[] alf = {'A','B','C','D','E','F','G','H','I','J','K','L',
                'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        int count = 0;
        for(int i = 0; i < asd.length;i++){
            for(int j = 0;j<alf.length;j++){
                if(asd[i] == alf[j]){
                    count++;
                }
            }
        }
        if(count > 0){
            return true;
        }else{
            return false;
        }
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
