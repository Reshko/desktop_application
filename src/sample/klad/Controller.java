package sample.klad;

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
import sample.connectToDb.TovarList;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private Button update;

    private ObservableList<TovarList> tovarData = FXCollections.observableArrayList();

    private ObservableList<Textile> textileData = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<TovarList> tableUsers;

    @FXML
    private TableColumn<TovarList, Integer> idColumn;

    @FXML
    private TableColumn<TovarList, String> nameColumn;

    @FXML
    private TableColumn<TovarList, Integer> widthColumn;

    @FXML
    private TableColumn<TovarList, Integer> longColumn;

    @FXML
    private TableColumn<TovarList, Integer> numberColumn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button changeBtn;

    @FXML
    private Button addBtn;

    @FXML
    private TableView<Textile> tableUsers1;

    @FXML
    private TableColumn<Textile, Integer> idColumn2;

    @FXML
    private TableColumn<Textile, String> typeColumn;

    @FXML
    private TableColumn<Textile, String> countryColumn;

    @FXML
    private TableColumn<Textile, String> colorColumn;

    @FXML
    private TableColumn<Textile, Integer> longColumn2;

    @FXML
    private TableColumn<Textile, Integer> widthColumn2;


    @FXML
    void initialize() {

        showTable();
        idColumn.setCellValueFactory(new PropertyValueFactory<TovarList,Integer>("idTovar"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<TovarList,String>("nameTovar"));
        widthColumn.setCellValueFactory(new PropertyValueFactory<TovarList,Integer>("widthTovar"));
        longColumn.setCellValueFactory(new PropertyValueFactory<TovarList,Integer>("longTovar"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<TovarList,Integer>("numberTovar"));
        tableUsers.setItems(tovarData);


        showTable2();
        idColumn2.setCellValueFactory(new PropertyValueFactory<Textile,Integer>("id"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Textile,String>("type"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<Textile,String>("country"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<Textile,String>("color"));
        longColumn2.setCellValueFactory(new PropertyValueFactory<Textile,Integer>("longer"));
        widthColumn2.setCellValueFactory(new PropertyValueFactory<Textile,Integer>("width"));
        tableUsers1.setItems(textileData);


        update.setOnAction(event -> {
            update.getScene().getWindow().hide();
            update();
        });




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

        changeBtn.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/klad/changeWindow/app.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent parent = loader.getRoot();
            Stage stage =  new Stage();
            stage.setTitle("Запрос на обновление данных");
            stage.setScene(new Scene(parent));
            stage.showAndWait();
        });

        deleteBtn.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/klad/deleteWindow/app.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent parent = loader.getRoot();
            Stage stage =  new Stage();
            stage.setTitle("Запрос на удаление данных");
            stage.setScene(new Scene(parent));
            stage.showAndWait();
        });

    }
    public void showTable(){

        DatabaseHandler dbHandler = new DatabaseHandler();

        String query = "select * from tovar2";
        try {
            Statement statement = dbHandler.getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                tovarData.add(new TovarList(resultSet.getInt("idtovar2"),resultSet.getString("nameTovar"),resultSet.getInt("widthTovar"),resultSet.getInt("longTovar"),resultSet.getInt("numberTovar"),1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void update(){
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
        showTable();
    }

    public void showTable2(){

        DatabaseHandler dbHandler = new DatabaseHandler();

        String query = "select * from textile";
        try {
            Statement statement = dbHandler.getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                textileData.add(new Textile(resultSet.getInt("idtextile"),resultSet.getString("typeTextile"),resultSet.getString("countryTextile"),resultSet.getString("colorTextile"),resultSet.getInt("longTextile"),resultSet.getInt("widtTextile")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
