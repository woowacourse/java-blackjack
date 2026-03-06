package domain;

import dto.GameStatus;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {

    private final List<GameStatus> games;

    public ScoreBoard() {
        this.games = new ArrayList<>();
    }

    public void record(GameStatus status) {

    }
}
