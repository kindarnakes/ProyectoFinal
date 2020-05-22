package Proyecto.controllers;

import Proyecto.App;
import Proyecto.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Ymedia {

    @FXML
    private Button add;
    @FXML
    private ImageView rival1;
    @FXML
    private ImageView rival2;
    @FXML
    private ImageView rival3;
    @FXML
    private ImageView rival4;
    @FXML
    private ImageView rival5;
    @FXML
    private ImageView rival6;
    @FXML
    private ImageView rival7;
    @FXML
    private ImageView rival8;
    @FXML
    private ImageView rival9;
    @FXML
    private ImageView rival10;
    @FXML
    private ImageView player1;
    @FXML
    private ImageView player2;
    @FXML
    private ImageView player3;
    @FXML
    private ImageView player4;
    @FXML
    private ImageView player5;
    @FXML
    private ImageView player6;
    @FXML
    private ImageView player7;
    @FXML
    private ImageView player8;
    @FXML
    private ImageView player9;
    @FXML
    private ImageView player10;
    @FXML
    private ImageView rivalImage;
    @FXML
    private ImageView playerImage;
    @FXML
    private Label playerscore;
    @FXML
    private Label rivalscore;
    @FXML
    private Label winner;
    @FXML
    private Button exit;

    private HashMap<Integer, ImageView> rivalCards;
    private HashMap<Integer, ImageView> playerCards;
    private boolean init = false;
    private SieteYMedia juego;
    private User AI;

    @FXML
    public void initialize() throws SQLException {
        Image img = new Image("/CardsImages/cubierta.png");
        rival1.setImage(img);
        rival2.setImage(img);
        rival3.setImage(img);
        rival4.setImage(img);
        rival5.setImage(img);
        rival6.setImage(img);
        rival7.setImage(img);
        rival8.setImage(img);
        rival9.setImage(img);
        rival10.setImage(img);
        player1.setImage(img);
        player2.setImage(img);
        player3.setImage(img);
        player4.setImage(img);
        player5.setImage(img);
        player6.setImage(img);
        player7.setImage(img);
        player8.setImage(img);
        player9.setImage(img);
        player10.setImage(img);
        img = new Image("/AI/AI.gif");
        rivalImage.setImage(img);
        Blob player = Data.getINSTANCE().get_logged().get_img();
        if (player != null) {
            img = new Image(player.getBinaryStream());
            playerImage.setImage(img);
        }

    }


    @FXML
    public void pedir() {
        if (!init) {
            rivalCards = new HashMap<>();
            playerCards = new HashMap<>();
            for (int i = 1; i <= 10; i++) {
                ImageView imgr = (ImageView) this.add.getScene().lookup("#rival" + i);
                ImageView imgp = (ImageView) this.add.getScene().lookup("#player" + i);
                rivalCards.put(i, imgr);
                playerCards.put(i, imgp);
            }

            AI = new User();
            AI.set_username("AI");
            init = true;
            ArrayList<User> _users = new ArrayList<>();
            _users.add(AI);
            _users.add(Data.getINSTANCE().get_logged());
            this.juego = new SieteYMedia(_users);
        }

        if (this.juego != null) {
            if (this.juego.score(Data.getINSTANCE().get_logged()) < 7.5) {
                this.juego.turn(Data.getINSTANCE().get_logged());
                ArrayList<Card> cards = this.juego.playersCards(Data.getINSTANCE().get_logged());
                Iterator<Card> it = cards.iterator();
                int ncards = cards.size();
                for (int i = 1; i <= ncards && i <= 10; i++) {
                    String name = it.next().getName();
                    this.playerCards.get(i).setImage(new Image("/CardsImages/" + name + ".png"));
                }
                playerscore.setText(this.juego.score(Data.getINSTANCE().get_logged()) + "");
                if (this.juego.score(Data.getINSTANCE().get_logged()) >= 7.5) {
                    doAI();
                    winner.setText("Gana: " + this.juego.winner().get_username());
                    exit.setVisible(true);
                }
            }

        }

    }

    @FXML
    public void stop() {
        if (init) {
            doAI();
            winner.setText("Gana: " + this.juego.winner().get_username());
            exit.setVisible(true);
        }
    }

    private void doAI() {
        ArrayList<Card> cards;
        Iterator<Card> it;
        int ncards;
        this.juego.AI(AI);
        this.rivalscore.setText(this.juego.score(AI) + "");
        cards = this.juego.playersCards(AI);
        it = cards.iterator();
        ncards = cards.size();
        for (int i = 1; i <= ncards && i <= 10; i++) {
            String name = it.next().getName();
            this.rivalCards.get(i).setImage(new Image("/CardsImages/" + name + ".png"));
        }
    }

    @FXML
    public void back() throws IOException {
        App.setRoot("Profile");
    }


}
