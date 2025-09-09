package controller.roomController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Room;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomManagementController implements RoomManagementInterface{
    ObservableList<Room> infoList = FXCollections.observableArrayList();
    
    @Override
    public ObservableList<Room> getAllinfo() {
        
        if (! infoList.isEmpty()){
            infoList.clear();
        }

        try {
            ResultSet resultSet = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM room_info;").executeQuery();

            while (resultSet.next()){
                infoList.add(
                        new Room(
                                resultSet.getInt("room_number"),
                                resultSet.getString("room_type"),
                                resultSet.getString("description"),
                                resultSet.getString("meals"),
                                resultSet.getDouble("price_per_night"),
                                resultSet.getString("room_status")
                        )
                );
            }
        } catch (SQLException e) {
            showMessage(e.getSQLState()+"\n"+e.getMessage());
        }
        return infoList;
    }

    private void showMessage(String mesage){
        JOptionPane.showMessageDialog(null, mesage);
    }
}
