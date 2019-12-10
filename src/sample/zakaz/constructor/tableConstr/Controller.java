package sample.zakaz.constructor.tableConstr;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.connectToDb.DatabaseHandler;
import sample.director.Tovar;
import sample.zakaz.constructor.Constructor;

public class Controller {

    private ObservableList<Constructor> tovarData = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button update;

    @FXML
    private Button exitBtn;

    @FXML
    private TableView<Constructor> tableUsers1;

    @FXML
    private TableColumn<Constructor, Integer> wee;

    @FXML
    private TableColumn<Constructor, Integer> h;

    @FXML
    private TableColumn<Constructor, Integer> v;

    @FXML
    private TableColumn<Constructor, Integer> o;

    @FXML
    private TableColumn<Constructor, Integer> f;

    @FXML
    private TableColumn<Constructor, Integer> s;

    @FXML
    void initialize() {

        showTable();

        wee.setCellValueFactory(new PropertyValueFactory<Constructor, Integer>("w"));
        h.setCellValueFactory(new PropertyValueFactory<Constructor, Integer>("h"));
        v.setCellValueFactory(new PropertyValueFactory<Constructor, Integer>("v"));
        o.setCellValueFactory(new PropertyValueFactory<Constructor, Integer>("q"));
        f.setCellValueFactory(new PropertyValueFactory<Constructor, Integer>("f"));
        s.setCellValueFactory(new PropertyValueFactory<Constructor, Integer>("s"));

        tableUsers1.setItems(tovarData);


        update.setOnAction(event -> {
            update.getScene().getWindow().hide();
            update();
        });
        exitBtn.setOnAction(event -> {
            exitBtn.getScene().getWindow().hide();
            exist();
        });


    }
    public void showTable(){
        DatabaseHandler dbHandler = new DatabaseHandler();
        String query = "select * from konstructor";
        try {
            Statement statement = dbHandler.getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                tovarData.add(new Constructor(resultSet.getInt("widthh"),resultSet.getInt("highh"),resultSet.getInt("varik"),resultSet.getInt("okon"),resultSet.getInt("fur"),resultSet.getInt("ssize")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void update(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/zakaz/constructor/tableConstr/app.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent parent = loader.getRoot();
        Stage stage =  new Stage();
        stage.setTitle("Конструктор");
        stage.setScene(new Scene(parent));
        stage.show();
        showTable();
    }
    void exist(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../app.fxml"));
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
    }
}
