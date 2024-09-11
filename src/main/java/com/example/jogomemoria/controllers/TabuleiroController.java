package com.example.jogomemoria.controllers;

import com.example.jogomemoria.JogoApplication;
import com.example.jogomemoria.models.Tabuleiro;
import com.example.jogomemoria.services.TabuleiroService;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class TabuleiroController {

  @FXML
  public GridPane matrizCartas;

  @FXML
  public Label jogadorAtual;

  @FXML
  public Label pontuacao;

  @FXML
  public Label pontuacaoText;

  private Tabuleiro tabuleiro;

  private TabuleiroService tabuleiroService;

  @FXML
  public void initialize() {
    tabuleiro = new Tabuleiro();
    tabuleiroService = new TabuleiroService(this);
    List<Color> coresList = tabuleiro.getCartas();
    int x = 0;
    for (int linha = 0; linha < 4; linha++) {
      for (int coluna = 0; coluna < 4; coluna++) {
        StackPane carta = criarCarta(coresList.get(x));
        x++;
        matrizCartas.add(carta, coluna, linha);
      }
    }
  }

  private StackPane criarCarta(Color cor) {
    tabuleiroService.inverterJogadores();
    return tabuleiroService.jogada(cor);
  }

  public void atualizarNome(String nome) {
    jogadorAtual.setText(nome);
  }

  public void atualizarPontuacao(int pontuacaoAtual) {
    pontuacao.setText(String.valueOf(pontuacaoAtual));
  }

  public void mudarParaFormulario() {
    Platform.runLater(() -> {
      try {
        Parent parent = new FXMLLoader(JogoApplication.class.getResource("/com/example/jogomemoria/formulario.fxml")).load();
        Stage stage = (Stage) matrizCartas.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }
}
