package domain;


public class User extends Participant{

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

    public GameResult getGameResult() {
        return gameResult;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }
}
