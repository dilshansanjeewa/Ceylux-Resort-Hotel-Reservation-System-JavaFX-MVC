package controller.roomController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Room;

import javax.swing.*;
import java.sql.PreparedStatement;
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

    @Override
    public boolean addNewRoom(Room room) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO room_info (room_number,room_type, description, meals, price_per_night, room_status) VALUES (?, ?, ?, ?, ?, ?);");
            preparedStatement.setObject(1, room.getRoomNumber());
            preparedStatement.setObject(2, room.getRoomType());
            preparedStatement.setObject(3, room.getDescription());
            preparedStatement.setObject(4, room.getMealStatus());
            preparedStatement.setObject(5, room.getPricePerNight());
            preparedStatement.setObject(6, room.getRoomStatus());

            if (0 < preparedStatement.executeUpdate()){
                return true;
            }
        } catch (SQLException e) {
            showMessage(e.getSQLState()+"\n"+e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteRoom(String roomNumber) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM room_info WHERE room_number = ?;");
            preparedStatement.setObject(1, roomNumber);
            if (0 < preparedStatement.executeUpdate()){
                return true;
            }
        } catch (SQLException e) {
            showMessage(e.getSQLState()+"\n"+e.getMessage());
        }
        return false;
    }

    private void showMessage(String mesage){
        JOptionPane.showMessageDialog(null, mesage);
    }
}
