package sample.director;

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

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller {

    private ObservableList<Tovar> tovarData = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Tovar> tableUsers;

    @FXML
    private TableColumn<Tovar, Integer> idColumn;

    @FXML
    private TableColumn<Tovar, String> nameColumn;

    @FXML
    private TableColumn<Tovar, Integer> widthColumn;

    @FXML
    private TableColumn<Tovar, Integer> longColumn;

    @FXML
    private TableColumn<Tovar, Integer> numberColumn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button changeBtn;

    @FXML
    private Button addBtn;


    @FXML
    void initialize() {

        showTable();

        idColumn.setCellValueFactory(new PropertyValueFactory<Tovar,Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Tovar,String>("name"));
        widthColumn.setCellValueFactory(new PropertyValueFactory<Tovar,Integer>("width"));
        longColumn.setCellValueFactory(new PropertyValueFactory<Tovar,Integer>("longer"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<Tovar,Integer>("number"));

        tableUsers.setItems(tovarData);


        exitBtn.setOnAction(event -> {
            exitBtn.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../sample.fxml"));

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

    }
    public void showTable(){

        DatabaseHandler dbHandler = new DatabaseHandler();

        String query = "select * from tovar";
        try {
            Statement statement = dbHandler.getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                tovarData.add(new Tovar(resultSet.getInt("idtovar"),resultSet.getString("name"),resultSet.getInt("width"),resultSet.getInt("long"),resultSet.getInt("number")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
