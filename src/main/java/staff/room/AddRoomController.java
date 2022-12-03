package staff.room;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AddRoomController {
    private AccessFileRoom accessFileRoom;
    private Room room;
    @FXML Button backBtn,addRoomBtn;
    @FXML ComboBox<String> buildBox,floorBox,roomBox,typeRoomBox;


    @FXML public void initialize() throws IOException {
        accessFileRoom = new MakefileRoom("data","Room.csv");
        room = new Room();
        room = accessFileRoom.getRoomList();
        setOptions();
    }
    @FXML public void setOptions(){
        buildBox.getItems().addAll("A","B");
        floorBox.getItems().addAll("1","2","3","4","5","6","7","8");
        roomBox.getItems().addAll("0","1","2","3","4","5","6","7","8","9");
        buildBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                typeRoomBox.getItems().clear();
                if(buildBox.getValue().equals("A")){
                    typeRoomBox.getItems().addAll("Studio suite");
                }else if (buildBox.getValue().equals("B")){
                    typeRoomBox.getItems().addAll("Deluxe suite");
                }
            }
        });
    }

    @FXML public void handleBackBtnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StaffDorm.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();
    }

    @FXML public void handleAddRoomBtn(ActionEvent event) throws IOException {
        accessFileRoom = new MakefileRoom("data", "Room.csv");
        if (buildBox.getValue() == null || floorBox.getValue() == null || roomBox.getValue() == null || typeRoomBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "error !", ButtonType.OK);
            alert.setContentText("Blank");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }
        } else {
            if (!room.checkRoom(buildBox.getValue(),floorBox.getValue(),roomBox.getValue())) {
                int maxRes = 1;
                Room tmp = new Room(buildBox.getValue(), floorBox.getValue(), roomBox.getValue(), typeRoomBox.getValue(), "None", maxRes);
                room.add(tmp);
                accessFileRoom.setRooms(room);
                Alert alert = new Alert(Alert.AlertType.NONE,"confirm",ButtonType.OK);
                alert.setContentText("Success !");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK){

                    Button b = (Button) event.getSource();
                    Stage stage = (Stage) b.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/StaffDorm.fxml"));
                    stage.setScene(new Scene(loader.load(), 800, 600));
                    stage.show();
                }

            } else {

                Alert alert = new Alert(Alert.AlertType.WARNING,"error",ButtonType.OK);
                alert.setContentText("Already have this room");
                alert.showAndWait();
                alert.close();

                }

        }

    }
}
