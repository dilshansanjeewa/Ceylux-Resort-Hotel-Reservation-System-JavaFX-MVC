package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;

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
    private TableView<?> tblRoomDetails;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtRoomNumber;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        System.out.println(comboRoomType.getValue());
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnExitOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> comboRoomTypeList = FXCollections.observableArrayList();
        comboRoomTypeList.addAll("Single", "Double", "Twin", "Suite", "Studio", "Villa");
        comboRoomType.setItems(comboRoomTypeList);
    }

    private String getMealStatus(){
        if(radioYes.isSelected()){
            return "Yes";
        }else {
            return "No";
        }
    }

    private String getRoomStatus(){
        if(radioAvailable.isSelected()){
            return "Available";
        } else if (radioBooked.isSelected()) {
            return "Booked";
        } else {
            return "Maintaining";
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
}
