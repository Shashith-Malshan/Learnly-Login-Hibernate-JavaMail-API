package controller;

import controller.impl.NewUserImpl;
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

public class NewUserFormController {
    Stage stage=new Stage();
    NewUser newUser=new NewUserImpl();

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

        String id=getNextId(getLastId());
        String email=txtEmail.getText();
        String password=txtPassword.getText();

        newUser.save(new Student(id,email,password));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successfully Created");
        alert.setHeaderText(null);
        alert.setContentText("Your Student Id is "+id);
        alert.showAndWait();


    }

    private String getNextId(String lastId) {
        if (lastId == null || lastId.isEmpty()) {
            return "S001";
        }

        int number = Integer.parseInt(lastId.substring(1));
        number++;

        return String.format("S%03d", number);
    }

    private String getLastId() {
        return newUser.getLastId();
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
