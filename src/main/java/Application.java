import game.BlackjackGame;

public class Application {

    public static void main(String[] args) {
        BlackjackConfig blackjackConfig = new BlackjackConfig();
        BlackjackGame blackjackGame = blackjackConfig.blackjackGame();
        blackjackGame.execute();
    }
}
