package com.example.jogomemoria.controllers;

import com.example.jogomemoria.JogoApplication;
import com.example.jogomemoria.models.JogadorModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class FormularioController {
  @FXML
  private TextField txtName1;
  @FXML
  private TextField txtName2;
  @FXML
  private TextField txtName3;
  @FXML
  private TextField txtName4;
  @FXML
  private Button btnIniciar;

  public ArrayList<JogadorModel> jogadoresList = new ArrayList<>();

  public void salvarJogadores(ArrayList<TextField> nomeJogadores) {
    for(TextField nome: nomeJogadores) {
      jogadoresList.add(new JogadorModel(nome.toString()));
    }
  }

  @FXML
  public void mudarTela(ActionEvent actionEvent) throws IOException {
    Parent parent = new FXMLLoader(JogoApplication.class.getResource("tabuleiro.fxml")).load();
    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    Scene scene = new Scene(parent);
    stage.setScene(scene);
    stage.show();
  }
}
