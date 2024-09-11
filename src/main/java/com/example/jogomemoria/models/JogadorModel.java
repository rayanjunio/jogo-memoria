package com.example.jogomemoria.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JogadorModel {

  @JsonProperty("nome")
  private String nome;
  @JsonProperty("pontos")
  private int pontos;

  public JogadorModel(String nome) {
    this.nome = nome;
    this.pontos = 0;
  }

  public JogadorModel() {}

  public String getNome() {
    return nome;
  }

  public int getPontos() {
    return pontos;
  }

  public void adicionarPontuacao() {
    this.pontos++;
  }

  @Override
  public String toString() {
    return "JogadorModel{" +
            "nome='" + nome + '\'' +
            ", pontos=" + pontos +
            '}';
  }
}
