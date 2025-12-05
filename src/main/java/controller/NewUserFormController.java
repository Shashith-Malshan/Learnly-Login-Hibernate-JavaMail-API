package controller;

import config.EmailConfig;
import controller.impl.NewUserImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Student;

import java.io.IOException;

public class NewUserFormController {

    Stage stage=new Stage();
    NewUser newUser=new NewUserImpl();
    EmailConfig emailConfig=new EmailConfig();

    private String otp;

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
    private Text txtStatus;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void createAccountOnAction(ActionEvent event) {

        String id=getNextId(getLastId());
        String email=txtEmail.getText();
        String password=txtPassword.getText();


        if(txtStatus.getText().equalsIgnoreCase("Verified")){
            newUser.save(new Student(id,email,password));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successfully Created");
            alert.setHeaderText(null);
            alert.setContentText("Your Student Id is "+id);
            alert.showAndWait();
        }else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Failed to Create");
            alert.setHeaderText(null);
            alert.setContentText("Verify the email and Try again");
            alert.showAndWait();
        }
    }

    public boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) return false;


        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
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
        otp=generateOTP();
        String email=txtEmail.getText();

        if(isValidEmail(email)){
            try {
                emailConfig.sendOtpEmail(email,otp);
                otp1.requestFocus();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Enter a Valid email");
            alert.showAndWait();
        }


    }

    private String generateOTP() {
        int otp = (int) (Math.random() * 9000) + 1000;
        return String.valueOf(otp);
    }

    @FXML
    void loginOnMouseClicked(MouseEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage currentStage= (Stage) txtLogin.getScene().getWindow();
        currentStage.close();
    }

    public void verifyOtp(KeyEvent keyEvent) {

        String enteredOtp=otp1.getText().trim()+otp2.getText().trim()+otp3.getText().trim()+otp4.getText().trim();

        if(enteredOtp.equals(otp)){
            otp1.setEditable(false);
            otp2.setEditable(false);
            otp3.setEditable(false);
            otp4.setEditable(false);
            txtEmail.setEditable(false);
            txtStatus.setFill(Color.GREEN);
            txtStatus.setText("Verified");
        }else {
            txtStatus.setFill(Color.RED);
            txtStatus.setText("Not Verified");
        }
    }
}
