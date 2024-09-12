package com.example.jogomemoria.services;

import com.example.jogomemoria.controllers.TabuleiroController;
import com.example.jogomemoria.models.Carta;
import com.example.jogomemoria.models.JogadorModel;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TabuleiroService {

  private ArrayList<Carta> parAtual = new ArrayList<>(2);

  private ArrayList<Carta> cartasViradas = new ArrayList<>();

  private JogadorServico jogadorServico = new JogadorServico();

  private ArrayList<JogadorModel> jogadoresList = jogadorServico.lerDados();

  private TabuleiroController tabuleiroController;

  public JogadorModel jogadorAtual;

  private int indiceJogadorAtual;

  public TabuleiroService(TabuleiroController tabuleiroController) {
    this.tabuleiroController = tabuleiroController;
    indiceJogadorAtual = 0;
    tabuleiroController.atualizarNome(jogadoresList.get(indiceJogadorAtual).getNome());
  }

  public StackPane jogada(Color cor) {
    InputStream questionImagem = getClass().getResourceAsStream("/imagens/question.png");
    ImageView imageView = new ImageView(new Image(questionImagem));
    imageView.setFitHeight(90);
    imageView.setFitWidth(90);

    Rectangle rectangle = new Rectangle(90, 90);
    rectangle.setFill(cor);

    StackPane carta = new StackPane(imageView);
    Carta cartaImagem = new Carta(carta, imageView, rectangle);

    carta.setOnMouseClicked(event -> virarCarta(cartaImagem));
    return carta;
  }

  private void virarCarta(Carta carta) {
    if (cartasViradas.contains(carta) || parAtual.contains(carta)) {
      return;
    }
    if (parAtual.size() < 2) {
      carta.mostrarFrente();
      parAtual.add(carta);

      if (parAtual.size() == 2) {
        verificarIguais();
      }
    }
  }

  private void verificarIguais() {
    Carta carta1 = parAtual.get(0);
    Carta carta2 = parAtual.get(1);

    if (carta1.getCor().equals(carta2.getCor())) {
      cartasViradas.add(carta1);
      cartasViradas.add(carta2);
      pontuar();
      parAtual.clear();
      if (cartasViradas.size() == 16) {
        alertaGanhador();
        reiniciarJogo();
      }
    } else {
      new Thread(() -> {
        try {
          Thread.sleep(300);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        Platform.runLater(() -> {
          carta1.mostrarVerso();
          carta2.mostrarVerso();
          parAtual.clear();
          inverterJogadores();
        });
      }).start();
    }
  }

  public void inverterJogadores() {
    indiceJogadorAtual++;
    if (indiceJogadorAtual >= jogadoresList.size()) {
      indiceJogadorAtual = 0;
    }
    jogadorAtual = jogadoresList.get(indiceJogadorAtual);
    tabuleiroController.atualizarNome(jogadorAtual.getNome());
    tabuleiroController.atualizarPontuacao(jogadorAtual.getPontos());
  }

  private void pontuar() {
    jogadorAtual = jogadoresList.get(indiceJogadorAtual);
    jogadorAtual.adicionarPontuacao();
    tabuleiroController.atualizarPontuacao(jogadorAtual.getPontos());
    jogadorServico.salvarPontuacao(jogadorAtual);
  }

  private void alertaGanhador() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("");
    alert.setContentText(exibirGanhador());
    alert.show();
  }

  private String exibirGanhador() {
    int maiorPontuacao = Collections
            .max(jogadoresList, Comparator.comparing(JogadorModel::getPontos)).getPontos();

    List<JogadorModel> vencedores = jogadoresList
            .stream()
            .filter(jogador -> jogador.getPontos() == maiorPontuacao)
            .toList();

    if (vencedores.size() > 1) {
      return "Empate entre " + vencedores.stream().map(JogadorModel::getNome).collect(Collectors.joining(" e "));
    }
    return vencedores.get(0).getNome() + " Venceu!!!";
  }

  private void reiniciarJogo() {
    tabuleiroController.mudarParaFormulario();
  }
}
