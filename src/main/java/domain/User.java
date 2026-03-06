package domain;


public class User extends Participant{

    private final String name;
    private GameResult gameResult;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    public GameResult getGameResult() {
        return gameResult;
    }
}
