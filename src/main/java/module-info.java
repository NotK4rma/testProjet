module com.projet.projet {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.xml.dom;


    opens com.projet.projet to javafx.fxml;
    exports com.projet.projet;
}