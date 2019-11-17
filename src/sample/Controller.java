

/**
 * Sample Skeleton for 'sample.fxml' Controller Class
 */

package sample;

        import java.io.IOException;
        import java.net.URL;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.SQLOutput;
        import java.util.ResourceBundle;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.PasswordField;
        import javafx.scene.control.TextField;
        import javafx.stage.Stage;
        import sample.connectToDb.DatabaseHandler;
        import sample.connectToDb.User;
        import java.sql.ResultSet;

public class Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="login"
    private TextField login; // Value injected by FXMLLoader

    @FXML // fx:id="password"
    private PasswordField password; // Value injected by FXMLLoader

    @FXML // fx:id="signUp"
    private Button signUp; // Value injected by FXMLLoader

    @FXML // fx:id="loginSignBtn"
    private Button loginSignBtn; // Value injected by FXMLLoader

    @FXML
    private Button checkPass;

    @FXML
    private Label labelId;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {


        signUp.setOnAction(event -> {
            String loginText = login.getText().trim();
            String passwordText = password.getText().trim();

            if(!loginText.equals("") && !passwordText.equals("")){
                newLoginUser(loginText,passwordText);
            }else {
                error();
            }

        });

        checkPass.setOnAction(event -> {
            //System.out.println(passField.getText());
            labelId.setText(password.getText());
        });



        loginSignBtn.setOnAction(event -> {
            loginSignBtn.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/app.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent parent = loader.getRoot();
            Stage stage =  new Stage();
            stage.setTitle("Окно регистрации");
            stage.setScene(new Scene(parent));
            stage.showAndWait();

        });
    }

    private void newLoginUser(String loginText, String passwordText) {
        DatabaseHandler dbHandel = new DatabaseHandler();
        User user = new User();

        user.setLoginUser(loginText);
        user.setPasswordUser(passwordText);
        ResultSet result = dbHandel.getUser(user);




        int counter = 0;
        String g = ("ghbdtn");

        try {
            while(result.next()){
                 g = result.getString("userstype");
                counter++;

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(counter>=1){
            System.out.println("ПОЛЬЗОВАТЕЛЬ НАЙДЕН");
            switch (g){
                case ("Кладовщик"): klad();
                    break;
                case ("Директор"): director();
                    break;
                case ("Менеджер"): meneger();
                    break;
                case ("Заказщик"): zakaz();
                    break;
            }
        }else {
            System.out.println("Не найдено");
            error();
        }
    }
    void director(){
        System.out.println("Вы вошли под директором");
        signUp.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/director/app.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent parent = loader.getRoot();
        Stage stage =  new Stage();
        stage.setTitle("Окно директора");
        stage.setScene(new Scene(parent));
        stage.show();
    }
    void meneger(){
        System.out.println("Вы вошли под менеджером");
        signUp.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/meneger/app.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent parent = loader.getRoot();
        Stage stage =  new Stage();
        stage.setTitle("Окно менеджера");
        stage.setScene(new Scene(parent));
        stage.show();
    }
    void klad(){
        signUp.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/klad/app.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent parent = loader.getRoot();
        Stage stage =  new Stage();
        stage.setTitle("Окно кладовщика");
        stage.setScene(new Scene(parent));
        stage.show();
    }
     void zakaz(){
        signUp.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/zakaz/app.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent parent = loader.getRoot();
        Stage stage =  new Stage();
        stage.setTitle("Окно заказчика");
        stage.setScene(new Scene(parent));
        stage.show();
    }
    void error(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/error/falseDate.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent parent = loader.getRoot();
        Stage stage =  new Stage();
        stage.setTitle("Ошибка");
        stage.setScene(new Scene(parent));
        stage.showAndWait();
    }
}


