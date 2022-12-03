package account;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class ChangePasswordController {
    private AccessFile accessFile;
    private Account account;
    @FXML TextField username;
    @FXML PasswordField password,confirm;
    @FXML Button backBtn;
    @FXML Button acceptBtn;

    public void initialize() throws IOException{
        accessFile = new MakefileAccount("data","Account.csv");
        account = accessFile.getAccountList();

    }
    @FXML public void handleBackBtnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();
    }
    @FXML public void handleAcceptBtnAction(ActionEvent event) throws IOException{
        int check = 0;
        for(Account acc:account.getAccounts()){
            if(acc.getUsername().equals(username.getText()) && password.getText().equals(confirm.getText())){
                check = 1;
                acc.setPassword(password.getText());
                accessFile.setAccounts(account);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Success", ButtonType.OK);
                alert.setContentText("Successful !");
                alert.showAndWait();
                alert.close();

                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
                stage.setScene(new Scene(loader.load(), 800, 600));
                stage.show();
            }

        }
        if (check == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"not match", ButtonType.OK);
            alert.setContentText("Password not match");
            alert.showAndWait();
            password.clear();
            confirm.clear();
        }
    }
}
