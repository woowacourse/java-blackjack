package domain;

import java.util.ArrayList;
import java.util.List;

public class User extends Player {

    private final String name;
    private GameResult gameResult;

    private User(String name) {
        this.name = name;
    }

    public static User from(String input) {
        return new User(input);
    }

    public String getName() {
        return name;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    public GameResult getGameResult() {
        return gameResult;
    }
}
