module fitnessclub.demoproj3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens fitnessclubjavafx to javafx.fxml;
    exports fitnessclubjavafx;
}