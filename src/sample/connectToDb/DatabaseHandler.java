package sample.connectToDb;

import sample.connectToDb.Configs;
import sample.connectToDb.Const;
import sample.zakaz.basketTovar.ZakazTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException,SQLException {

        String connectionString ="jdbc:mysql://" + dbHost + ":" + dbPort +"/" + dbName + "?" + "useSSL=true&verifyServerCertificate=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPassword);

        return dbConnection;
    }

    public void reqAddNewZakaz(TovarList tovarList){
        try {
            String insert = "INSERT INTO zakaz (nameZakaz,numberZakaz) VALUES (?, ?)";
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,tovarList.getNameTovar());
            prSt.setInt(2,tovarList.getNumberTovar());
            prSt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void reqDeleteZakazInBasket(ZakazTable zakaz){
        try {
            String delete = "DELETE FROM zakaz WHERE idZakaz =?";
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);
            prSt.setInt(1,zakaz.getIdTable());
            prSt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    public void reqAddNewTovar(TovarList tovarList){
        try {
            String insert = "INSERT INTO tovar2 (nameTovar, widthTovar, longTovar, numberTovar) VALUES (?, ?, ?, ?)";
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,tovarList.getNameTovar());
            prSt.setInt(2,tovarList.getWidthTovar());
            prSt.setInt(3,tovarList.getLongTovar());
            prSt.setInt(4,tovarList.getLongTovar());

            prSt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void reqDeleteTovar(TovarList tovarList){
        try {
            String delete = "DELETE FROM tovar2 WHERE idtovar2 =?";
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);
            prSt.setInt(1,tovarList.getIdTovar());
            prSt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }



    public void reqChangeTovar(TovarList tovarList){
        try {
            String update = "UPDATE tovar2 SET nameTovar =?, widthTovar =?, longTovar =? , numberTovar =? WHERE idtovar2 =?";
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.setString(1,tovarList.getNameTovar());
            prSt.setInt(2,tovarList.getWidthTovar());
            prSt.setInt(3,tovarList.getLongTovar());
            prSt.setInt(4,tovarList.getNumberTovar());
            prSt.setInt(5,tovarList.getIdTovar());
            prSt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }


    public void sighUpUser(User user){

        try {
            String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_TYPE + "," + Const.USER_LOGIN + "," + Const.USER_PASSWORD + ")" + "VALUES(?,?,?)";
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,user.getTypeUser());
            prSt.setString(2,user.getLoginUser());
            prSt.setString(3,user.getPasswordUser());

            prSt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user){
        ResultSet resSet = null;

        //String select = "SELECT * FROM " + Const.USER_TABLE +"WHERE"+ Const.USER_LOGIN + "=? AND " + Const.USER_PASSWORD + "=?" ;
        String select = "SELECT * FROM users WHERE userslogin =? AND userspassword =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,user.getLoginUser());
            prSt.setString(2,user.getPasswordUser());

            resSet = prSt.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public ResultSet checkLogin(User user){
        ResultSet  resSet = null;
        String select = "SELECT * FROM users WHERE userslogin =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,user.getLoginUser());
            resSet = prSt.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        return resSet;
    }
    public ResultSet checkZakazCount(TovarList tovar){
        ResultSet resultSet = null;

        String select = "SELECT * FROM tovar2 WHERE nameTovar =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,tovar.getNameTovar());
            resultSet = prSt.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        return resultSet;
    }

    public void changeCountWhenNewZakaz(TovarList tovarList){
        try {
            String update = "UPDATE tovar2 SET numberTovar =? WHERE idtovar2 =?";
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.setInt(1,tovarList.getNumberTovar());
            prSt.setInt(2,tovarList.getIdTovar());
            prSt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }





    public ResultSet checkZakaz(TovarList tovar){
        ResultSet resultSet = null;

        String select = "SELECT * FROM tovar2 WHERE nameTovar =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,tovar.getNameTovar());
            resultSet = prSt.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        return resultSet;
    }


}
