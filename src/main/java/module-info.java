module com.example.jogomemoria {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.web;

  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  requires validatorfx;
  requires org.kordamp.ikonli.javafx;
  requires org.kordamp.bootstrapfx.core;
  requires eu.hansolo.tilesfx;
  requires com.almasb.fxgl.all;

  opens com.example.jogomemoria to javafx.fxml;
  exports com.example.jogomemoria;
  exports com.example.jogomemoria.controllers;
  opens com.example.jogomemoria.controllers to javafx.fxml;
}