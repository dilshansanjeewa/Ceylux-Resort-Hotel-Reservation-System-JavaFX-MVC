package controller.roomController;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Room;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RoomManagementFormController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colMeals;

    @FXML
    private TableColumn<?, ?> colPricePerNight;

    @FXML
    private TableColumn<?, ?> colRoomNumber;

    @FXML
    private TableColumn<?, ?> colRoomStatus;

    @FXML
    private TableColumn<?, ?> colRoomType;

    @FXML
    private JFXComboBox<String> comboRoomType;

    @FXML
    private ToggleGroup meals;

    @FXML
    private JFXRadioButton radioAvailable;

    @FXML
    private JFXRadioButton radioBooked;

    @FXML
    private JFXRadioButton radioMaintaining;

    @FXML
    private JFXRadioButton radioNo;

    @FXML
    private JFXRadioButton radioYes;

    @FXML
    private ToggleGroup status;

    @FXML
    private TableView<Room> tblRoomDetails;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtRoomNumber;

    private RoomManagementInterface roomManagement = new RoomManagementController();
    private Stage stage;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        if(validateInputFields()){
            try{
                Room room = new Room(
                        Integer.parseInt(txtRoomNumber.getText()),
                        comboRoomType.getValue(),
                        txtDescription.getText(),
                        getMealStatus(),
                        Double.parseDouble(txtPrice.getText()),
                        getRoomStatus()
                );

                if (roomManagement.addNewRoom(room)){
                   showMessage("Room added to the system succrssfully...");
                   clear();
                   loadTable();
                }else {
                    showMessage("ERROR...\nRoom adding process failed...Please try again...");
                }
            }catch (NumberFormatException exception){
                showMessage("WRONG INPUT...\nPlease input correct Price...");
            }
        }

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if(txtRoomNumber.getText().isEmpty() || txtRoomNumber.getText() == null){
            showMessage("ERROR...\nPlease input Room Number...");
            return;
        }

        if (roomManagement.deleteRoom(txtRoomNumber.getText())){
            showMessage("Room details removed successfully...");
            clear();
            loadTable();
        }else {
            showMessage("ERROR...Room details removing process faild...Please try again...");
        }
    }

    @FXML
    void btnExitOnAction(ActionEvent event) {
        try {
            stage= (Stage) btnExit.getScene().getWindow();
            stage.setTitle("Ceylux Resort Dashboard");
            stage.setResizable(false);
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Dash_board_Form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (validateInputFields()){
            try{
                Room room = new Room(
                        Integer.parseInt(txtRoomNumber.getText()),
                        comboRoomType.getValue(),
                        txtDescription.getText(),
                        getMealStatus(),
                        Double.parseDouble(txtPrice.getText()),
                        getRoomStatus()
                );

                if(roomManagement.updateInfo(room)){
                    showMessage("Room details updated successfully...");
                    clear();
                    loadTable();
                }else {
                    showMessage("ERROR...\nRoom details updating process failed... Please try again...");
                }
            }catch (NumberFormatException e){
                showMessage("WRONG INPUT...\nPlease input correct Price...");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> comboRoomTypeList = FXCollections.observableArrayList();
        comboRoomTypeList.addAll("Single", "Double", "Twin", "Suite", "Studio", "Villa");
        comboRoomType.setItems(comboRoomTypeList);

        colRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colMeals.setCellValueFactory(new PropertyValueFactory<>("mealStatus"));
        colPricePerNight.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        colRoomStatus.setCellValueFactory(new PropertyValueFactory<>("roomStatus"));

        loadTable();

        tblRoomDetails.getSelectionModel().selectedItemProperty().addListener((observableValue, room, info) ->{
            if(info != null){
                setDetails(info);
            }
        } );
    }

    private void setDetails(Room info) {
        txtRoomNumber.setText(String.valueOf(info.getRoomNumber()));
        comboRoomType.setValue(info.getRoomType());
        txtDescription.setText(info.getDescription());
        setMealStatus(info.getMealStatus());
        txtPrice.setText(String.valueOf(info.getPricePerNight()));
        setRoomStatus(info.getRoomStatus());
    }

    private void setRoomStatus(String roomStatus) {
        if (roomStatus.equals("Available")){
            radioAvailable.setSelected(true);
        } else if (roomStatus.equals("Booked")) {
            radioBooked.setSelected(true);
        } else {
            radioMaintaining.setSelected(true);
        }
    }

    private void setMealStatus(String mealStatus) {
        if(mealStatus.equals("Yes")){
            radioYes.setSelected(true);
        }else {
            radioNo.setSelected(true);
        }
    }

    private void loadTable(){
        tblRoomDetails.setItems(roomManagement.getAllinfo());
    }

    private String getMealStatus(){
        if(radioYes.isSelected()){
            return "Yes";
        } else if(radioNo.isSelected()){
            return "No";
        }else {
            return null;
        }
    }

    private String getRoomStatus(){
        if(radioAvailable.isSelected()){
            return "Available";
        } else if (radioBooked.isSelected()) {
            return "Booked";
        } else if (radioMaintaining.isSelected()){
            return "Maintaining";
        } else {
            return null;
        }
    }

    private void clear(){
        txtRoomNumber.setText(null);
        comboRoomType.setValue(null);
        txtDescription.setText(null);
        if(radioYes.isSelected()){
            radioYes.setSelected(false);
        }else{
            radioNo.setSelected(false);
        }
        txtPrice.setText(null);
        if(radioAvailable.isSelected()){
            radioAvailable.setSelected(false);
        } else if (radioBooked.isSelected()) {
            radioBooked.setSelected(false);
        } else {
          radioMaintaining.setSelected(false);
        }

    }

    private boolean validateInputFields(){
        if(txtRoomNumber.getText().isEmpty() || txtRoomNumber.getText() == null){
            showMessage("ERROR...\nPlease input Room Number...");
            return false;
        }
        if(comboRoomType.getValue() == null){
            showMessage("ERROR...\nPlease select a Room Type...");
            return false;
        }
        if(txtDescription.getText().isEmpty() || txtDescription.getText() == null){
            showMessage("ERROR...\nPlease input Description...");
            return false;
        }
        if(getMealStatus() == null){
            showMessage("ERROR...\nPlease select Meal Status...");
            return false;
        }
        if(txtPrice.getText().isEmpty() || txtPrice.getText() == null){
            showMessage("ERROR...\nPlease input Price Per Night...");
            return false;
        }
        if(getRoomStatus() == null){
            showMessage("ERROR...\nPlease select Room Status...");
            return false;
        }
        return true;
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}