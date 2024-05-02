package com.example.javafxrestapi;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainViewController {
    @FXML
    private TextField urlName;

    @FXML
    private Button btnGetReq;

    @FXML
    private Button btnPostReq;

    @FXML
    private Button btnDeleteReq;

    @FXML
    private TabPane tabPan;

    @FXML
    private Tab responseTab;

    @FXML
    private Tab contentTab;

    @FXML
    private TextArea responseText;

    @FXML
    private TextArea contentText;

    @FXML
    void showAbout(ActionEvent event) throws IOException {
        System.out.println("show about page");
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("about.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("About");
        stage.setResizable(false);
        stage.sizeToScene();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/tsp-rounded-logo.png")));
        stage.show();
    }

    @FXML
    void exitApplication(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void clearContent(ActionEvent event) {
        contentText.clear();
    }
    @FXML
    void clearResponse(ActionEvent event) {
        responseText.clear();
    }
    @FXML
    void clearUrl(ActionEvent event) {
        urlName.clear();
    }

    @FXML
    void makeGetReq(MouseEvent event) {
        System.out.println("Making get req");
        try {
            URL url = new URL(sanitizeUrl(urlName.getText()));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            responseText.setText("HttpResponseCode: " + responseCode + " - " + conn.getResponseMessage() + "\n");
            StringBuilder informationString = new StringBuilder();
            InputStream inputStream;
            try{
                inputStream = conn.getInputStream();
            } catch (IOException e){
                inputStream = conn.getErrorStream();
            }
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bf.readLine()) != null) {
                informationString.append(line);
            }
            responseText.appendText(informationString.toString());
            tabPan.getSelectionModel().select(responseTab);
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Successfully get request done.");
    }
    @FXML
    void makePostReq(MouseEvent event) {
        System.out.println("Making post req");
        try{
            String body = contentText.getText();
            URL url = new URL(sanitizeUrl(urlName.getText()));
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(body);
            int responseCode = conn.getResponseCode();
            responseText.setText("HttpResponseCode: " + responseCode + " - " + conn.getResponseMessage() + "\n");
            StringBuilder informationString = new StringBuilder();
            InputStream inputStream;
            try{
                inputStream = conn.getInputStream();
            } catch (IOException e){
                inputStream = conn.getErrorStream();
            }
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bf.readLine()) != null) {
                informationString.append(line);
            }
            responseText.appendText(informationString.toString());
            tabPan.getSelectionModel().select(responseTab);
            conn.disconnect();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Successfully post request done.");
    }
    @FXML
    void makePutReq(MouseEvent event) throws IOException {
        System.out.println("Making put req");
        try{
            String body = contentText.getText();
            URL url = new URL(sanitizeUrl(urlName.getText()));
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(body);
            int responseCode = conn.getResponseCode();
            responseText.setText("HttpResponseCode: " + responseCode + " - " + conn.getResponseMessage() + "\n");
            StringBuilder informationString = new StringBuilder();
            InputStream inputStream;
            try{
                inputStream = conn.getInputStream();
            } catch (IOException e){
                inputStream = conn.getErrorStream();
            }
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bf.readLine()) != null) {
                informationString.append(line);
            }
            responseText.appendText(informationString.toString());
            tabPan.getSelectionModel().select(responseTab);
            conn.disconnect();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Successfully put request done.");
    }
    @FXML
    void makeDeleteReq(MouseEvent event) {
        System.out.println("Making delete req");
        try{
            String body = contentText.getText();
            URL url = new URL(sanitizeUrl(urlName.getText()));
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(body);
            int responseCode = conn.getResponseCode();
            responseText.setText("HttpResponseCode: " + responseCode + " - " + conn.getResponseMessage() + "\n");
            StringBuilder informationString = new StringBuilder();
            InputStream inputStream;
            try{
                inputStream = conn.getInputStream();
            } catch (IOException e){
                inputStream = conn.getErrorStream();
            }
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bf.readLine()) != null) {
                informationString.append(line);
            }
            responseText.appendText(informationString.toString());
            tabPan.getSelectionModel().select(responseTab);
            conn.disconnect();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Successfully delete request done.");
    }

    private String sanitizeUrl(String url){
        return url.trim();
    }
}