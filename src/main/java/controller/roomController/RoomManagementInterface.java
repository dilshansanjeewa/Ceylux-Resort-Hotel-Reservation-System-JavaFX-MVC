package controller.roomController;

import javafx.collections.ObservableList;
import model.Room;

public interface RoomManagementInterface{
    ObservableList<Room> getAllinfo();
    boolean addNewRoom(Room room);
    boolean deleteRoom(String roomNumber);
}
