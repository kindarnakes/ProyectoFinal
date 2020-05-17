package Proyecto.controllers;

import Proyecto.model.Data;
import Proyecto.model.Score;
import Proyecto.model.ScoreDAO;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class Scores {

    private ObservableList<Score> _list;
    private String game = "";

    @FXML
    private Button back;
    @FXML
    private Button delete;
    @FXML
    private Button updateTable;
    @FXML
    private Label title;
    @FXML
    private TableView<Score> scores;
    @FXML
    private TableColumn<Score, String> username;
    @FXML
    private TableColumn<Score, Integer> score;

    public void setGame(String g) {
        this.game = g;
    }

    public void setTableTitle(String s) {
        this.title.setText(s);
    }

    @FXML
    public void show() {
        _list = FXCollections.observableList(ScoreDAO.getScoreTableByGame(game));
        scores.setItems(_list);
        username.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get_user()));
        score.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().get_score()).asObject());
    }

    @FXML
    private void back() {
        Stage stage = (Stage) this.back.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void delete() {
        ScoreDAO.deleteScoreGameByUsername(Data.getINSTANCE().get_logged().get_username(), this.game);
        this.show();
    }


}
