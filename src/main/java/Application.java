import controller.BlackjackGame;
import domain.strategy.RandomShuffleStrategy;
import domain.strategy.ShuffleStrategy;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ShuffleStrategy shuffleStrategy = new RandomShuffleStrategy();

        BlackjackGame blackjackGame = new BlackjackGame(inputView, outputView, shuffleStrategy);
        blackjackGame.run();
    }
}
