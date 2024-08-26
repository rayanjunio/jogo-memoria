package com.example.jogomemoria.controllers;

import com.example.jogomemoria.models.Carta;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TabuleiroController {

  @FXML
  public GridPane matrizCartas;

  private ArrayList<Carta> parAtual = new ArrayList<>(2);

  private ArrayList<Carta> cartasViradas = new ArrayList<>();

  private final Color[] cores = {
          Color.RED, Color.RED,
          Color.BLUE, Color.BLUE,
          Color.GREEN, Color.GREEN,
          Color.YELLOW, Color.YELLOW,
          Color.ORANGE, Color.ORANGE,
          Color.PURPLE, Color.PURPLE,
          Color.PINK, Color.PINK,
          Color.BROWN, Color.BROWN
  };

  @FXML
  public void initialize() {
    List<Color> coresList = Arrays.asList(cores);
    Collections.shuffle(coresList);
    int x = 0;
    for(int linha = 0; linha < 4; linha++) {
      for(int coluna = 0; coluna < 4; coluna++) {
        StackPane carta = criarCarta(coresList.get(x));
        x++;
        matrizCartas.add(carta, coluna, linha);
      }
    }
  }

  private StackPane criarCarta(Color cor) {
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
    if(cartasViradas.contains(carta)) {
      System.out.println("Não permitido!");
      return;
    }
    if (parAtual.size() < 2) {
      if (carta.isMostrandoFrente()) {
        return;
      }
      carta.mostrarFrente();
      parAtual.add(carta);

      if (parAtual.size() == 2) {
        verificarIguais();
      }
    }
  }

  public void verificarIguais() {

    Carta carta1 = parAtual.get(0);
    Carta carta2 = parAtual.get(1);

      if (carta1.getCor().equals(carta2.getCor())) {
        System.out.println("Cartas iguais");
        cartasViradas.add(carta1);
        cartasViradas.add(carta2);
        parAtual.clear();
      } else {
        abrirAlerta();
        System.out.println("Cartas diferentes");
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
          });
        }).start();
      }
  }

  private void abrirAlerta() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("");
    alert.setContentText("As cartas são diferentes!");
    alert.show();
  }
}
