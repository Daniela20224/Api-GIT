module com.mycompany.progra3api.git {
   requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;
    requires java.base;

    exports com.mycompany.progra3api.git;
    exports com.mycompany.progra3api.git.domain;
    exports com.mycompany.progra3api.git.service;
    exports com.mycompany.progra3api.git.fxml;

    opens com.mycompany.progra3api.git to javafx.fxml;
    opens com.mycompany.progra3api.git.fxml to javafx.fxml; 
}
    