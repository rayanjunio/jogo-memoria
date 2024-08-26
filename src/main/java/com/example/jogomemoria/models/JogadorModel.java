package com.example.jogomemoria.models;

public class JogadorModel {
  private String nome;
  private int pontos;

  public JogadorModel(String nome) {
    this.nome = nome;
    this.pontos = 0;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getPontos() {
    return pontos;
  }

  public void setPontos(int pontos) {
    this.pontos = pontos;
  }
}
