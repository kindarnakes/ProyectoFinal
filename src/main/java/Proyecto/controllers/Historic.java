package Proyecto.controllers;

import Proyecto.model.Data;
import Proyecto.model.Match;
import Proyecto.model.MatchDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.sql.Blob;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;

public class Historic {

    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private Label username1;
    @FXML
    private Label username2;
    @FXML
    private Label winner;
    @FXML
    private Label date;
    @FXML
    private Label game;
    @FXML
    private ListView<String> list;

    private ObservableList<Match> oMatch;
    private HashMap<String, Match> mMatch;

    @FXML
    public void initialize() {
        List<Match> matchs = MatchDAO.getByUser(Data.getINSTANCE().get_logged().get_username());
        List<String> rivals = new ArrayList<>();
        mMatch = new HashMap<>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        for (Match m : matchs) {
            String r = m.get_time().format(format);
            rivals.add(r);
            mMatch.put(r, m);
        }
        list.setItems(FXCollections.observableList(rivals));
    }

    @FXML
    public void back() {
        Stage stage = (Stage) this.username1.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void select() throws SQLException {
        String s = list.getSelectionModel().getSelectedItem();
        Match m = mMatch.get(s);
        username1.setText(m.get_user1().get_username());
        username2.setText(m.get_user2().get_username());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        date.setText(m.get_time().format(format));
        winner.setText(m.get_winner().get_username());
        game.setText(m.get_game());
        Blob img = m.get_user1().get_img();
        if (img != null) {
            img1.setImage(new Image(img.getBinaryStream()));
        } else {
            img1.setImage(null);
        }
        img = m.get_user2().get_img();
        if (img != null) {
            img2.setImage(new Image(m.get_user2().get_img().getBinaryStream()));
        } else {
            img2.setImage(null);
        }

    }

    @FXML
    public void delete() {
        String s = list.getSelectionModel().getSelectedItem();
        Match m = mMatch.get(s);
        MatchDAO.deleteMatch(m);
        this.initialize();
    }


}
