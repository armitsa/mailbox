package admin;

import account.Account;
import account.MakefileAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class AdminController {
    private Account staffs;
    private ObservableList<Account> staffList;
    private MakefileAccount accessFile;

    @FXML TableView<Account> staffListTable;
    @FXML TableColumn<Account,String> id,name,surname,dateAndTime;
    @FXML Button settingsBtn,addBtn,logoutBtn;

    @FXML public void initialize() throws IOException {
        accessFile = new MakefileAccount("data","Account.csv");
        staffs = accessFile.getAccountList();
        staffs.addStaff(staffs.getAccounts());
        setOptions();
    }

    @FXML public void setOptions(){
        staffList = FXCollections.observableList(staffs.getStaffsList());
        staffListTable.setItems(staffList);
        id.setCellValueFactory(new PropertyValueFactory<Account,String>("username"));
        name.setCellValueFactory(new PropertyValueFactory<Account,String>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<Account,String>("surname"));
        dateAndTime.setCellValueFactory(new PropertyValueFactory<Account,String>("dateAndTime"));
        dateAndTime.setSortType(TableColumn.SortType.DESCENDING);
        staffListTable.getSortOrder().add(dateAndTime);
    }

    @FXML public void handleSettingsAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChangePassword.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();
    }

    @FXML public void handleAddBtnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddStaff.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();
    }

    @FXML public void handleLogoutBtnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();
    }

}
