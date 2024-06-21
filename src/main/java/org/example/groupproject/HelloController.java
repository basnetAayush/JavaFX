package org.example.groupproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HelloController {
    @FXML
    private Label userINFO;

    @FXML
    private Label LoginINFO;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    // admin
    @FXML
    private TextField adminName;

    @FXML
    private TextField adminEmail;

    @FXML
    private  TextField adminUsername;

    @FXML
    private PasswordField adminPassword;

    @FXML
    private PasswordField adminConfirmPassword;

    // survey creator
    @FXML
    private TextField surveyCreatorFName;
    @FXML
    private TextField surveyCreatorLName;
    @FXML
    private TextField surveyCreatorEmail;
    @FXML
    private TextField surveyCreatorUsername;
    @FXML
    private PasswordField surveyCreatorPassword;
    @FXML
    private PasswordField surveyCreatorConfirmPassword;
    @FXML
    private ChoiceBox<String> SCreatorGender;
    @FXML
    private TextField surveyCreatorPhoneNumber;
    @FXML
    private TextField surveyCreatorDepartment;
    @FXML
    private TextField SurveyCreatorEmail;
    @FXML
    private PasswordField SurveyCreatorPassword;

    // User
    @FXML
    private TextField UserSignUpName;
    @FXML
    private TextField UserSignUpEmail;
    @FXML
    private TextField UserSignUpUsername;
    @FXML
    private PasswordField UserSignUpPassword;
    @FXML
    private PasswordField UserSignUpConfirmPassword;
    @FXML
    private TextField UserSignUpNo;

    @FXML
    public void initialize() {
        if (SCreatorGender != null) {
            SCreatorGender.getItems().addAll("Male", "Female", "Other");
        } else {
            System.err.println("SCreatorGender is not initialized.");
        }
    }

    String pathToAdminCSV = "C:\\Users\\ACER\\Downloads\\groupProject-JavaFX--master\\groupProject-JavaFX--master\\src\\main\\resources\\org\\example\\groupproject\\AdminData.csv";
    String pathToSurveyCData = "C:\\Users\\ACER\\Downloads\\groupProject-JavaFX--master\\groupProject-JavaFX--master\\src\\main\\resources\\org\\example\\groupproject\\SurveyCreatorData.csv";
    String pathToUserData = "C:\\Users\\ACER\\Downloads\\groupProject-JavaFX--master\\groupProject-JavaFX--master\\src\\main\\resources\\org\\example\\groupproject\\UserCsv.csv";
    public void buttonForAdminSignIn() throws IOException{
        loadStage("Admin.fxml");
    }

    public void buttonForAdminSignUp(ActionEvent event) throws IOException{
        try {
//            loadStage("AdminSignUp.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminSignUp.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = Uses.getCurrentStage(event);
            stage.setScene(scene);
        }catch (Exception e){
            e.getMessage();
        }
    }

    public void buttonForCreatorSignIn() throws IOException{
        loadStage("/org/example/groupproject/SurveyCreatorSignIn.fxml");
    }

    public void buttonForUserSignIn() throws IOException{
        loadStage("/org/example/groupproject/UserSignIn.fxml");
    }

    public void buttonForAdminRegister() {
        String AdminName = adminName.getText();
        String AdminEmail = adminEmail.getText();
        String AdminUsername = adminUsername.getText();
        String AdminPassword = adminPassword.getText();
        String AdminConformPassword = adminConfirmPassword.getText();
        try {
            FileWriter fileWriter = new FileWriter(pathToAdminCSV, true);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            String[] data = {AdminName, AdminEmail, AdminUsername, AdminPassword, AdminConformPassword};
            csvWriter.writeNext(data);
            csvWriter.close();
            loadStage("/org/example/groupproject/Admin.fxml");

        } catch (Exception e) {
            userINFO.setText(e.getMessage());
        }
    }

    public void buttonForLogin(ActionEvent event){
        String emailText = email.getText();
        String passwordText = password.getText();
        try {
            FileReader fileReader = new FileReader(pathToAdminCSV);
            CSVReader csvReader = new CSVReader(fileReader);
            String[] rows;
            boolean found = false;
            while ((rows = csvReader.readNext()) != null){
                if (emailText.equals(rows[1]) && passwordText.equals(rows[3])){
                    LoginINFO.setText("Login Successful");
//                    loadStage("AdminDashboard.fxml");
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = Uses.getCurrentStage(event);
                    stage.setScene(scene);
                    found = true;
                    break;
                }
            }
            if (!found) {
                LoginINFO.setText("Invalid Credentials");
            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }



    public void buttonForAdminLogin()throws IOException{
        loadStage("/org/example/groupproject/Admin.fxml");
    }

    // survey creator...........................................
// for login
    public void buttonForSurveyCreatorSignUp(ActionEvent event) throws IOException{
        // loadStage("AdminSignUp.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SurveyCreatorSignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = Uses.getCurrentStage(event);
        stage.setScene(scene);
    }

    public void buttonForSurveyCreatorLogin(ActionEvent event){
        String emailText = SurveyCreatorEmail.getText();
        String passwordText = SurveyCreatorPassword.getText();
        try {
            FileReader fileReader = new FileReader(pathToSurveyCData);
            CSVReader csvReader = new CSVReader(fileReader);
            String[] rows;
            boolean found = false;
            while ((rows = csvReader.readNext()) != null){
                if (emailText.equals(rows[2]) && passwordText.equals(rows[7])){
                    LoginINFO.setText("Login Successful");
                    //loadStage("/org/example/groupproject/AdminDashboard.fxml");
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = Uses.getCurrentStage(event);
                    stage.setScene(scene);
                    found = true;
                    break;
                }
            }
            if (!found) {
                LoginINFO.setText("Invalid Credentials");
            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

    // for register
    public void buttonForSurveyCreatorSignIn(ActionEvent event) {
        String CreatorFName = surveyCreatorFName.getText();
        String CreatorLName = surveyCreatorLName.getText();
        String CreatorEmail = surveyCreatorEmail.getText();
        String CreatorUsername = surveyCreatorUsername.getText();
        String CreatorDepartment = surveyCreatorDepartment.getText();
        String CreatorGender = SCreatorGender.getValue();
        String CreatorNo = surveyCreatorPhoneNumber.getText();
        String CreatorPassword = surveyCreatorPassword.getText();
        String CreatorConformPassword = surveyCreatorConfirmPassword.getText();
        try {
            FileWriter fileWriter = new FileWriter(pathToSurveyCData, true);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            String[] data = {CreatorFName, CreatorLName, CreatorEmail, CreatorUsername, CreatorGender,CreatorDepartment,
                    CreatorNo, CreatorPassword, CreatorConformPassword};
            csvWriter.writeNext(data);
            csvWriter.close();
            loadStage("/org/example/groupproject/SurveyCreatorSignIn.fxml");
        } catch (Exception e) {
            userINFO.setText(e.getMessage());
        }
    }



    // User
   // for login
    public void buttonForUserSignUp(ActionEvent event) throws IOException{
        // loadStage("AdminSignUp.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserSignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = Uses.getCurrentStage(event);
        stage.setScene(scene);
    }

    public void buttonForUserLogin(ActionEvent event){
        String emailText = UserSignUpEmail.getText();
        String passwordText = UserSignUpPassword.getText();
        try {
            FileReader fileReader = new FileReader(pathToUserData);
            CSVReader csvReader = new CSVReader(fileReader);
            String[] rows;
            boolean found = false;
            while ((rows = csvReader.readNext()) != null){
                if (rows.length >= 7 && emailText.equals(rows[1]) && passwordText.equals(rows[6])){ // Check row length before accessing
                    //if (emailText.equals(rows[1]) && passwordText.equals(rows[6])){
                    LoginINFO.setText("Login Successful");
                    //loadStage("/org/example/groupproject/AdminDashboard.fxml");
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = Uses.getCurrentStage(event);
                    stage.setScene(scene);
                    found = true;
                    break;
                }
            }
            if (!found) {
                LoginINFO.setText("Invalid Credentials");
            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

    // for register
    public void buttonForUserSignUpRegister(ActionEvent event) {
        String ParticipantName = UserSignUpName.getText();
        String ParticipantEmail = UserSignUpEmail.getText();
        String ParticipantUsername = UserSignUpUsername.getText();
        String ParticipantGender = SCreatorGender.getValue();
        String ParticipantNo = UserSignUpNo.getText();
        String ParticipantPassword = UserSignUpPassword.getText();
        String ParticipantConformPassword = UserSignUpConfirmPassword.getText();
        try {
            FileWriter fileWriter = new FileWriter(pathToUserData, true);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            String[] data = {ParticipantName, ParticipantEmail, ParticipantUsername, ParticipantGender,
                    ParticipantNo, ParticipantPassword, ParticipantConformPassword};
            csvWriter.writeNext(data);
            csvWriter.close();
            loadStage("/org/example/groupproject/UserSignIn.fxml");
        } catch (Exception e) {
            userINFO.setText(e.getMessage());
        }
    }

    public void buttonForUserSignUpLogin()throws IOException{
        loadStage("/org/example/groupproject/UserSignIn.fxml");
    }





    @FXML
    public void loadStage(String sceneName) throws IOException{
        try {
            System.out.println("Loading FXML file: " + sceneName);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sceneName));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) userINFO.getScene().getWindow();
            stage.setScene(new Scene (root));
            stage.show();
        }catch (IOException e){
            userINFO.setText("Failed to load scene: " + e.getMessage());
            e.printStackTrace();
        }
    }



}