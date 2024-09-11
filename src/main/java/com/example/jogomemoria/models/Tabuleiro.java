package com.example.jogomemoria.models;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Tabuleiro {
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

  private List<Color> cartas = new ArrayList<>();

  public Tabuleiro() {
    iniciarCartas();
  }

  private void iniciarCartas() {
    List<Color> coresList = Arrays.asList(cores);
    Collections.shuffle(coresList);
    for (Color cor : coresList) {
      cartas.add(cor);
    }
  }

  public List<Color> getCartas() {
    return cartas;
  }
}
