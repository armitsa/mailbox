package staff.item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class StaffItemController {

    private ObservableList<Item> itemList;
    private AccessFileItem accessFileItem;
    private Item items = new Item();

    @FXML TableView<Item> itemListTable;
    @FXML TableColumn<Item,String> type,room,date,nameRec,surRec,size,nameSend,surSend,address,priority,deliComp,tracking;
    @FXML Button backBtn,addBtn,removeBtn,searchBtn,refreshBtn,sortBtn;
    @FXML TextField roomText;
    @FXML ComboBox<String> sortBox;

    @FXML public void initialize() throws IOException {
        accessFileItem = new MakeFileItem("data","Item.csv");
        items = accessFileItem.getItemList();
        items.addTempObject(items.getItems());
        setOptions();
        sortBox.getItems().addAll("Recently","Oldest","most value room","min value room");
    }
    @FXML public void setOptions(){
        itemList = FXCollections.observableList(items.getTempItems());
        itemListTable.setItems(itemList);
        type.setCellValueFactory(new PropertyValueFactory<>("typeItem"));
        room.setCellValueFactory(new  PropertyValueFactory<>("fullNumRoom"));
        date.setCellValueFactory(new PropertyValueFactory<>("dateAndTime"));
        nameRec.setCellValueFactory(new PropertyValueFactory<>("nameRec"));
        surRec.setCellValueFactory(new PropertyValueFactory<>("surnameRec"));
        size.setCellValueFactory(new  PropertyValueFactory<>("size"));
        nameSend.setCellValueFactory(new PropertyValueFactory<>("nameSend"));
        surSend.setCellValueFactory(new PropertyValueFactory<>("surnameSend"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        deliComp.setCellValueFactory(new PropertyValueFactory<>("deliveryComp"));
        tracking.setCellValueFactory(new PropertyValueFactory<>("tracking"));

        if (String.valueOf(sortBox.getValue()).equals("Oldest")){
            date.setSortType(TableColumn.SortType.ASCENDING);
            itemListTable.getSortOrder().add(date);
        }
        else if (String.valueOf(sortBox.getValue()).equals("most value room")){
            room.setSortType(TableColumn.SortType.DESCENDING);
            itemListTable.getSortOrder().add(room);
        }
        else if (String.valueOf(sortBox.getValue()).equals("min value room")){
            room.setSortType(TableColumn.SortType.ASCENDING);
            itemListTable.getSortOrder().add(room);
        }else{
            date.setSortType(TableColumn.SortType.DESCENDING);
            itemListTable.getSortOrder().add(date);
        }
    }

    @FXML public void handleBackBtnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Staff.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();
    }
    @FXML public void handleRefreshBtnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StaffItem.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();
    }


    @FXML public void handleAddBtnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddItem.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();
    }
    public void handleSearchBtnOnAction(){
        items.addTempObject(roomText.getText());
        setOptions();
    }
    @FXML public void handleRemoveBtnAction(ActionEvent event) throws IOException{
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RemoveItem.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();
    }
    @FXML public void handleSortAction(ActionEvent event) {
    setOptions();
    }
}

