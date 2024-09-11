package com.example.jogomemoria.services;

import com.example.jogomemoria.models.JogadorModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.util.ArrayList;

public class JogadorServico {
  public JogadorServico() {}

  private final String caminhoArquivo = "src/main/resources/dados-jogadores/jogadores.json";

  ObjectMapper mapper = new ObjectMapper();
  ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();

  public void salvarDados(ArrayList<JogadorModel> jogadoresList) {
    try {
      PrintStream out = new PrintStream(new FileOutputStream(caminhoArquivo));

      String json = writer.writeValueAsString(jogadoresList);
      out.println(json);
      out.close();

      System.out.println("funcionou");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("erro");
    }
  }

  public ArrayList<JogadorModel> lerDados() {
    ArrayList<JogadorModel> jogadoresList = new ArrayList<>();

    try {
      File file = new File(caminhoArquivo);
      if(file.exists() && file.length() > 0) {
        jogadoresList = mapper.readValue(file, new TypeReference<>() {
        });
        System.out.println(jogadoresList);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return jogadoresList;
  }

  public void salvarPontuacao(JogadorModel jogador) {
    try {
      ArrayList<JogadorModel> jogadoresList = lerDados();
      for(JogadorModel j : jogadoresList) {
        if(jogador.getNome().equalsIgnoreCase(j.getNome())) {
          j.adicionarPontuacao();
        }
      }
      PrintStream out = new PrintStream(new FileOutputStream(caminhoArquivo));

      String json = writer.writeValueAsString(jogadoresList);
      out.println(json);
      out.close();

      System.out.println("funcionou2");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("erro3");
    }
  }
}
