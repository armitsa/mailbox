package staff.room;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
public class AddUserController {
    private AccessFileRoom accessFileRoom;
    private Room room;
    @FXML Button backBtn, createBtn;
    @FXML TextField nameField;
    @FXML ComboBox<String> buildBox, floorBox, roomBox, typeRoomBox, name, maxRes, currentRes;

    @FXML
    public void initialize() throws IOException {
        accessFileRoom = new MakefileRoom("data", "Room.csv");
        room = new Room();
        room = accessFileRoom.getRoomList();
        setOptions();
    }

    @FXML public void setOptions() {
        buildBox.getItems().addAll("A", "B");
        floorBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8");
        roomBox.getItems().addAll("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        buildBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                typeRoomBox.getItems().clear();
                if (buildBox.getValue().equals("A")) {
                    typeRoomBox.getItems().addAll("Studio suite");
                } else if (buildBox.getValue().equals("B")) {
                    typeRoomBox.getItems().addAll("Deluxe suite");
                }
            }
        });
    }

    @FXML
    public void handleBackBtnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StaffDorm.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();
    }

    @FXML
    public void handleCreateBtnAction(ActionEvent event) throws IOException {
        accessFileRoom = new MakefileRoom("data", "Room.csv");
        if(room.checkUser(buildBox.getValue(),floorBox.getValue(),roomBox.getValue(),nameField.getText())){
            accessFileRoom.setRooms(room);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "None", ButtonType.OK);
            alert.setContentText("Success");
            alert.showAndWait();
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/StaffDorm.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));
            stage.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "error", ButtonType.OK);
            alert.setContentText("Room not found or Already has user");
            alert.showAndWait();
            alert.close();
        }
    }
}

