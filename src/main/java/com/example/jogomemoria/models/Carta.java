package com.example.jogomemoria.models;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Carta implements VirarCarta{
  private StackPane carta;
  private ImageView imageView;
  private Rectangle rectangle;
  private boolean mostrandoFrente = false;

  public Carta(StackPane carta, ImageView imageView, Rectangle rectangle) {
    this.carta = carta;
    this.imageView = imageView;
    this.rectangle = rectangle;
  }

  @Override
  public void mostrarFrente() {
    carta.getChildren().setAll(rectangle);
  }

  @Override
  public void mostrarVerso() {
    carta.getChildren().setAll(imageView);
  }

  @Override
  public boolean isMostrandoFrente() {
    return mostrandoFrente;
  }

  public Color getCor() {
    return (Color) rectangle.getFill();
  }
}
