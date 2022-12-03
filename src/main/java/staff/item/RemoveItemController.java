package staff.item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class RemoveItemController {
    private AccessFileItem accessFileItem;
    private Item items;
    @FXML Button backBtn,removeBtn;
    @FXML TextField build,floor,room;

    @FXML public void initialize() throws IOException {
        accessFileItem = new MakeFileItem("data","Item.csv");
        items = new Item();
        items = accessFileItem.getItemList();
    }
    @FXML
    public void handleBackBtnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StaffItem.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();

    }
    @FXML public void handleRemoveBtnAction(ActionEvent event) throws IOException {
        if (items.checkRoom(build.getText(),floor.getText(),room.getText())){
            items.getItems().removeIf(n->(n.getFullNumRoom().equals(build.getText()+floor.getText()+room.getText())));
            accessFileItem.setItems(items);
            Alert alert = new Alert(Alert.AlertType.NONE,"confirm", ButtonType.OK);
            alert.setContentText("Successful !");
            alert.showAndWait();
            alert.close();

            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/StaffItem.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Not found", ButtonType.OK);
            alert.setContentText("Not found this room");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK){
                build.clear();
                floor.clear();
                room.clear();
            }
            else {
                alert.close();
            }
        }
    }
}