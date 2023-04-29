package com.warehouse_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ConnectPSQL {
 
    static final String DB_URL = "jdbc:postgresql://localhost:5432/warehouse";
    static final String USER = "postgres";
    static final String PASS = "1111";

    
    public static ObservableList<Unit> getTable(String zapros) throws Exception{

        ObservableList<Unit> list = FXCollections.observableArrayList();

        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(DB_URL,USER, PASS);

        PreparedStatement statement = connection.prepareStatement(zapros);
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()){
            Unit unit = new Unit(
                resultSet.getInt("unitid"),
                resultSet.getString("unitname"), 
                resultSet.getString("unitdepartment"), 
                resultSet.getInt("unitcount")
                );
            list.add(unit);
        }
        connection.close();

        return list;
    }

    static public boolean getLogin(String login, String password) throws Exception{
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(DB_URL,USER, PASS);

        String zapros = "SELECT * FROM authorize WHERE userlogin = '" + login+"';";

        PreparedStatement statement = connection.prepareStatement(zapros);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            if(
               resultSet.getString("userlogin").equals(login) &&
               resultSet.getString("userpassword").equals(password)&&
               resultSet.getBoolean("userpermission") == true
            
            ) return true;
        }

        connection.close();

        return false;
    }

    public static void saveTable(ObservableList<Unit> list) throws Exception{
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(DB_URL,USER, PASS);

        PreparedStatement statement = connection.prepareStatement("DELETE FROM units;");
        statement.execute();

        for (Unit unit : list){
            String zapros = String.format("insert into units (unitID, unitName, unitDepartment, unitCount) values (%d, '%s', '%s', %d);",
            unit.getUnitID(), unit.getUnitName(), unit.getUnitDepartment(), unit.getUnitCount());
            statement = connection.prepareStatement(zapros);
            statement.execute();
        }
    }
}
