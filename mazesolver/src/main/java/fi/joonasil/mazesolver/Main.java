/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver;

import fi.joonasil.mazesolver.gui.Screen;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Ohjelman main luokka. Tällä hetkellä vielä vähän sotkuinen kun kaikkea testataan vielä.
 * Esim. kaikki käyttöliittymäsotkut menee omaan luokkaan.
 * 
 * !!!!!!! MITÄ VÄRIT TARKOITTAVAT?? !!!!!!!
 * Musta: Labyrintin seinä.
 * Valkoinen: Labyrintin käytävä. Jos jokin ratkaisualgoritmi on ajettu, niin käytävä,
 * jossa mikään algoritmi ei ole käynyt.
 * Vaaleansininen: Paras reitti labyrintin ylävasemmasta kulmasta alaoikeaan kulmaan.
 * Punainen: Labyrintin käytävät, missä leveyssuuntainen läpikäynti- algoritmi on käynyt.
 * Vihreä: Labyrintin käytävät, joissa a* on käynyt.
 * Oranssi: Labyrintin käytävät, joissa leveyssuuntainen läpikäynti ja a* molemmat ovat käyneet.
 * !!!!!!! ------------------------  !!!!!!!
 * 
 * @author Joonas
 */
public class Main extends Application {
    
     /**
     * Käynnistää ohjelman käyttöliittymän.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    } 
    
    @Override
    public void start(Stage primaryStage) {
        
        
        
        Screen screen = new Screen();
      
        primaryStage.setResizable(true);
        primaryStage.setTitle("Maze Solver!");
        primaryStage.setScene(screen.getScene());
        primaryStage.show();
        
    }
}
