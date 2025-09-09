package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;

public class RoomManagementFormController {

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
    private JFXComboBox<?> comboRoomType;

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
    private TableView<?> tblRoomDetails;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtRoomNumber;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

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

}
