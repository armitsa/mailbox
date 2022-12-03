package account;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class AliveDormitory extends Application {

    public static void main(String[] args) { launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        primaryStage.setTitle("AliveDormitory");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.getScene()
                .getStylesheets()
                .add("org/kordamp/bootstrapfx/bootstrapfx.css");
        primaryStage.setResizable(false);
        primaryStage.show();

    }

}
