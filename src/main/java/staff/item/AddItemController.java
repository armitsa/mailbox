package staff.item;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import staff.room.AccessFileRoom;
import staff.room.MakefileRoom;
import staff.room.Room;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AddItemController {
    private AccessFileItem accessFileItem;
    private Item items;
    private AccessFileRoom accessFileRoom;
    private Room rooms;
    @FXML Button backBtn,addParcel,refreshBtn;
    @FXML ComboBox<String> typeItem,building,floor,room,priority,deliveryComp;
    @FXML TextField nameRec,surnameRec;
    @FXML TextField size;
    @FXML TextField nameSend,surnameSend,address;
    @FXML TextField tracking;
    @FXML Label priorityLabel,deliveryLabel,trackingLabel;

    @FXML public void initialize() throws IOException {
        accessFileItem = new MakeFileItem("data","Item.csv");
        items = accessFileItem.getItemList();
        accessFileRoom = new MakefileRoom("data","Room.csv");
        rooms = accessFileRoom.getRoomList();
        setOptions();
    }
    @FXML public void setOptions(){
        typeItem.getItems().addAll("Mail","Document","Package");
        nameRec.getText();
        surnameRec.getText();
        building.getItems().addAll("A","B");
        floor.getItems().addAll("1","2","3","4","5","6","7","8");
        room.getItems().addAll("0","1","2","3","4","5","6","7","8","9");
        size.getText();
        nameSend.getText();
        surnameSend.getText();
        address.getText();
        typeItem.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (typeItem.getValue().equals("Document")){
                    priority.getItems().addAll("Express","Secret");
                    deliveryComp.setVisible(false);
                    deliveryLabel.setVisible(false);
                    tracking.setVisible(false);
                    trackingLabel.setVisible(false);

                }
                else if (typeItem.getValue().equals("Package")){
                    deliveryComp.getItems().addAll("Kerry","Thai Post","DHL","etc.");
                    tracking.getText();
                    priority.setVisible(false);
                    priorityLabel.setVisible(false);
                }
                else {
                    priority.setVisible(false);
                    priorityLabel.setVisible(false);
                    deliveryComp.setVisible(false);
                    deliveryLabel.setVisible(false);
                    tracking.setVisible(false);
                    trackingLabel.setVisible(false);
                }
            }
        });
    }
    @FXML public void handleAddItemAction(ActionEvent event) throws IOException{
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String tempDateTime = dateTime.format(formatter);
        if (rooms.checkRoom(String.valueOf(building.getValue()),String.valueOf(floor.getValue()),String.valueOf(room.getValue()))){
            if (String.valueOf(typeItem.getValue()).equals("Mail")){
                Mail mail = new Mail(String.valueOf(typeItem.getValue()),
                        items.concatenateRoom(String.valueOf(building.getValue()),String.valueOf(floor.getValue()),String.valueOf(room.getValue())),
                        nameRec.getText(),surnameRec.getText(),
                        size.getText(),
                        nameSend.getText(),surnameSend.getText(),
                        address.getText(),tempDateTime);
                items.add(mail);
            }
            else if (String.valueOf(typeItem.getValue()).equals("Document")){
                Document document = new Document(String.valueOf(typeItem.getValue()),
                        items.concatenateRoom(String.valueOf(building.getValue()), String.valueOf(floor.getValue()),String.valueOf(room.getValue())),
                        nameRec.getText(),surnameRec.getText(),
                        size.getText(),
                        nameSend.getText(),surnameSend.getText(),
                        address.getText(),tempDateTime,String.valueOf(priority.getValue()));
                items.add(document);
            }
            else if (String.valueOf(typeItem.getValue()).equals("Package")){
                Parcel parcel = new Parcel(String.valueOf(typeItem.getValue()),
                        items.concatenateRoom(String.valueOf(building.getValue()), String.valueOf(floor.getValue()),String.valueOf(room.getValue())),
                        nameRec.getText(),surnameRec.getText(),
                        size.getText(),
                        nameSend.getText(),surnameSend.getText(),
                        address.getText(),tempDateTime,String.valueOf(deliveryComp.getValue()),tracking.getText());
                items.add(parcel);
            }
            accessFileItem.setItems(items);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Success",ButtonType.OK);
            alert.setContentText("Success!");
            alert.showAndWait();
            alert.close();

            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/StaffItem.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"error",ButtonType.OK);
            alert.setContentText("not found this room");
            alert.showAndWait();
            alert.close();
        }
    }

    @FXML public void handleBackBtnAction(ActionEvent event) throws IOException {

        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StaffItem.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();
    }

    @FXML public void handleRefreshBtnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddItem.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();
    }

}
