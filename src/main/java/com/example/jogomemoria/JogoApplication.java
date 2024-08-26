package com.example.jogomemoria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class JogoApplication extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    Parent parent = new FXMLLoader(JogoApplication.class.getResource("jogo.fxml")).load();
    Scene scene = new Scene(parent);
    stage.setScene(scene);
    stage.setTitle("Jogo da Memória");
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}