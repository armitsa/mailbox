package admin;

import account.AccessFile;
import account.Account;
import account.MakefileAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;


public class AddStaffController {
    private AccessFile accessFile;
    private Account account;

    @FXML Button backBtn,createBtn;
    @FXML TextField idField,nameField,surField;
    @FXML PasswordField passField,confirmField;

    @FXML public void initialize() throws IOException{
        accessFile = new MakefileAccount("data","Account.csv");
        account = new Account();
        account = accessFile.getAccountList();
    }

    @FXML public void handleBackBtnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();
    }

    @FXML public void handleCreateBtnAction(ActionEvent event) throws IOException {
        if (!idField.getText().contains(",") && !passField.getText().contains(",") && !nameField.getText().contains(",") && !surField.getText().contains(",") &&
                !idField.getText().equals("") && !passField.getText().equals("") && !nameField.getText().equals("") && !surField.getText().equals("")){
            if (account.checkUsername(idField.getText())) {
                Account acc = new Account("Staff", idField.getText(), passField.getText(), nameField.getText(), surField.getText(), "-");
                if (!passField.getText().equals(confirmField.getText())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "not match", ButtonType.OK);
                    alert.setContentText("Password not match");
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        passField.clear();
                        confirmField.clear();
                    } else {
                        alert.close();
                    }
                } else {
                    account.add(acc);
                    accessFile.setAccounts(account);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Success", ButtonType.OK);
                    alert.setContentText("Successful !");
                    alert.showAndWait();
                    alert.close();

                    Button b = (Button) event.getSource();
                    Stage stage = (Stage) b.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin.fxml"));
                    stage.setScene(new Scene(loader.load(), 800, 600));
                    stage.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.NONE, "error", ButtonType.CLOSE);
                alert.setContentText("this username is already in use");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.CLOSE) {
                    idField.clear();
                    passField.clear();
                    confirmField.clear();
                } else {
                    alert.close();
                }
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Incomplete", ButtonType.OK);
            alert.setContentText("Incomplete information");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                nameField.clear();
                surField.clear();
                idField.clear();
                passField.clear();
                confirmField.clear();
            } else {
                alert.close();
            }
        }
    }


}


