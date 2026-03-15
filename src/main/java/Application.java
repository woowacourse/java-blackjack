import config.BlackjackGameConfiguration;
import controller.BlackjackGame;

public class Application {

    public static void main(String[] args) {
        BlackjackGameConfiguration configuration = new BlackjackGameConfiguration();
        BlackjackGame blackjackGame = BlackjackGame.from(configuration);
        blackjackGame.start();
    }

}
