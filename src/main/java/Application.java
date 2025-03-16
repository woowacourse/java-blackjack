import blackjack.BlackjackGame;
import blackjack.config.GameConfig;
import blackjack.view.ConsoleInputView;
import blackjack.view.ConsoleOutputView;

public class Application {

    public static void main(String[] args) {

        GameConfig gameConfig = new GameConfig(
                new ConsoleInputView(),
                new ConsoleOutputView()
        );
        BlackjackGame blackJackGame = new BlackjackGame(gameConfig);
        blackJackGame.run();
    }
}
