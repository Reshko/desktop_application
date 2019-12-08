package sample.zakaz.basketTovar;

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
import sample.zakaz.Tovar;


public class ControllerBasket {

    private ObservableList<ZakazTable> basketData = FXCollections.observableArrayList();



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button update;

    @FXML
    private TableView<ZakazTable> tableUsers;

    @FXML
    private TableColumn<ZakazTable, Integer> idColumn;

    @FXML
    private TableColumn<ZakazTable, String> nameColumn;

    @FXML
    private TableColumn<ZakazTable, Integer> numberColumn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button deleteZakazInBasket;

    @FXML
    void initialize() {

        showTable();

        idColumn.setCellValueFactory(new PropertyValueFactory<ZakazTable,Integer>("idTable"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<ZakazTable,String>("nameTable"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<ZakazTable,Integer>("numberTable"));

        tableUsers.setItems(basketData);

        exitBtn.setOnAction(event -> {
            exitBtn.getScene().getWindow().hide();
        });

        update.setOnAction(event -> {
            update.getScene().getWindow().hide();
            update();
        });

        deleteZakazInBasket.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/zakaz/basketTovar/deleteZakaz/app.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent parent = loader.getRoot();
            Stage stage =  new Stage();
            stage.setTitle("Запрос на добавление данных");
            stage.setScene(new Scene(parent));
            stage.showAndWait();
        });

    }

    public void showTable(){

        DatabaseHandler dbHandler = new DatabaseHandler();

        String query = "select * from zakaz";
        try {
            Statement statement = dbHandler.getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                basketData.add(new ZakazTable(resultSet.getInt("idZakaz"),resultSet.getString("nameZakaz"),resultSet.getInt("numberZakaz")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    void update(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/zakaz/basketTovar/app.fxml"));

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
        showTable();
    }

}








