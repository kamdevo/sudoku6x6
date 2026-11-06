module com.example.sudoku6x6 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.sudoku6x6.controllers to javafx.fxml;
    exports com.example.sudoku6x6;
}
