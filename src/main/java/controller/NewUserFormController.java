package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class NewUserFormController {
    Stage stage=new Stage();

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnGetOtp;

    @FXML
    private TextField otp1;

    @FXML
    private TextField otp2;

    @FXML
    private TextField otp3;

    @FXML
    private TextField otp4;

    @FXML
    private TextField txtEmail;

    @FXML
    private Text txtLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void createAccountOnAction(ActionEvent event) {

    }

    @FXML
    void getOtpOnAction(ActionEvent event) {

    }

    @FXML
    void loginOnMouseClicked(MouseEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
