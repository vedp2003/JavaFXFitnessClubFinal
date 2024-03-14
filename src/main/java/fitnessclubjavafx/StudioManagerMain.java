package fitnessclubjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class that launches the Java FX Studio Manager GUI application
 *
 * @author Ved Patel, Vivek Manthri
 */
public class StudioManagerMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudioManagerMain.class.getResource("studioManagerView.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 680, 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("RU Fitness Club - Studio Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}