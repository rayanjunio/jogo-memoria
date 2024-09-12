package com.example.jogomemoria.controllers;

import com.example.jogomemoria.JogoApplication;
import com.example.jogomemoria.models.JogadorModel;
import com.example.jogomemoria.services.JogadorServico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class FormularioController {
  @FXML
  private TextField txtNome1;
  @FXML
  private TextField txtNome2;
  @FXML
  private TextField txtNome3;
  @FXML
  private TextField txtNome4;

  private ArrayList<JogadorModel> jogadoresList = new ArrayList<>();

  private JogadorServico jogadorServico = new JogadorServico();

  public void salvarJogador() {
    jogadorServico.salvarDados(this.jogadoresList);
    System.out.println("oii");
  }

  @FXML
  public void mudarTela(ActionEvent actionEvent) throws IOException {
    List<TextField> nomes = Arrays.asList(txtNome1, txtNome2, txtNome3, txtNome4);
    Set<String> nomesExistentes = new HashSet<>();
    boolean verificarJogador = false;

    for (TextField nome : nomes) {
      String nomeJogador = nome.getText().trim();
      if (!nomeJogador.isEmpty()) {
        if(nomesExistentes.contains(nomeJogador)) {
          alertaNomesDuplicados();
          return;
        }
        jogadoresList.add(new JogadorModel(nome.getText()));
        nomesExistentes.add(nomeJogador);
        verificarJogador = true;
      }
    }

    if (!verificarJogador) {
      alertaFaltaJogadores();
      throw new RuntimeException("ERRO: Adicione jogadores para iniciar o jogo.");
    }

    salvarJogador();

    Parent parent = new FXMLLoader(JogoApplication.class.getResource("tabuleiro.fxml")).load();
    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    Scene scene = new Scene(parent);
    stage.setScene(scene);
    stage.show();
  }

  private void alertaFaltaJogadores() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText("");
    alert.setContentText("ERRO: Adicione jogadores para iniciar o jogo.");
    alert.show();
  }

  private void alertaNomesDuplicados() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText("");
    alert.setContentText("ERRO: Não é permitido nicknames duplicados!");
    alert.show();
  }
}
