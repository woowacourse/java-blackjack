import blackjack.BlackjackController;
import blackjack.config.GameConfig;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {

    public static void main(String[] args) {

        GameConfig gameConfig = new GameConfig(
                new InputView(),
                new OutputView()
        );
        BlackjackController blackJackController = new BlackjackController(gameConfig);
        blackJackController.run();
    }
}
