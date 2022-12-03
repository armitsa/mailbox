package staff.room;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class StaffDormController {

    @FXML TableView<Room> roomListTable;
    @FXML TableColumn building,floor,room,type, name;
    @FXML Button addUsersBtn,addRoomBtn,backBtn;

    private Room rooms;
    private ObservableList<Room> roomList;
    private AccessFileRoom accessFile;

    public void initialize() throws IOException {
        accessFile = new MakefileRoom("data","Room.csv");
        rooms = new Room();
        rooms = accessFile.getRoomList();
        roomList = FXCollections.observableList(rooms.getRooms());
        roomListTable.setItems(roomList);
        setOptions();
    }

    @FXML public void setOptions(){
        building.setCellValueFactory(new PropertyValueFactory<>("Building"));
        floor.setCellValueFactory(new PropertyValueFactory<>("Floor"));
        room.setCellValueFactory(new PropertyValueFactory<>("Room"));
        type.setCellValueFactory(new PropertyValueFactory<>("TypeRoom"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
    }

    @FXML public void handleBackBtnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Staff.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();
    }

    @FXML public void handleAddUsersBtnAction(ActionEvent event) throws IOException{
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddUser.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();
    }

    @FXML public void handleAddRoomBtnAction(ActionEvent event) throws IOException{
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddRoom.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();
    }


}
