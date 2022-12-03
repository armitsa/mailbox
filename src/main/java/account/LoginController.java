package account;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LoginController {
    @FXML TextField usernameField, passwordField;
    @FXML Button loginBtn,aboutMeBtn,guideBtn;
    private AccessFile file;
    private Account accounts;

    @FXML public void initialize() throws IOException {
        file = new MakefileAccount("data", "Account.csv");
        accounts = file.getAccountList();
    }

    @FXML
    public void handleAboutMeBtnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AboutMe.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();
    }
    @FXML
    public void handleGuideBtnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Guide.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();
    }

    @FXML
    public void handleLoginBtnAction(ActionEvent event) throws IOException {
        int check = 0;
        for (Account a : accounts.getAccounts()){
            if (a.getUsername().equals(usernameField.getText()) && a.getPassword().equals(passwordField.getText()) && a.getPermission().equals("Admin")) {
                    check = 1;
                    Button b = (Button) event.getSource();
                    Stage stage = (Stage) b.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin.fxml"));
                    stage.setScene(new Scene(loader.load(), 800, 600));
                    stage.show();

            } else if (a.getUsername().equals(usernameField.getText()) && a.getPassword().equals(passwordField.getText()) && a.getPermission().equals("Staff")) {
                    check = 1;
                    LocalDateTime dateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    String tempDateTime = dateTime.format(formatter);
                    a.setDateAndTime(tempDateTime);
                    file.setAccounts(accounts);

                    Button b = (Button) event.getSource();
                    Stage stage = (Stage) b.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Staff.fxml"));
                    stage.setScene(new Scene(loader.load(), 800, 600));
                    stage.show();
            }
        }
        if(check == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"not found",ButtonType.OK);
            alert.setContentText("Username or Password Incorrect");
            alert.showAndWait();
            usernameField.clear();
            passwordField.clear();
            alert.close();
        }
    }
}

