package controller;

import controller.impl.LoginImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Student;

import java.io.IOException;

public class LoginFormController {

    Stage stage=new Stage();
    Login login=new LoginImpl();

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Text txtSignUp;

    @FXML
    private TextField txtUsername;

    @FXML
    void loginOnAction(ActionEvent event) {
        String id=txtUsername.getText();
        String password=txtPassword.getText();

        Student student=login.getStudentById(id);
        if(student.getPassword().equals(password)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Status");
            alert.setHeaderText(null);
            alert.setContentText("Successfully logged in!");
            alert.showAndWait();
        }






    }

    @FXML
    void signUpOnClicked(MouseEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/NewUserForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();

    }

}
