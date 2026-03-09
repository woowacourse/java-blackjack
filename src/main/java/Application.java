import controller.BlackjackGame;
import domain.strategy.RandomShuffleStrategy;
import domain.strategy.ShuffleStrategy;
import util.InputParser;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        InputParser inputParser = new InputParser();
        OutputView outputView = new OutputView();
        ShuffleStrategy shuffleStrategy = new RandomShuffleStrategy();

        BlackjackGame blackjackGame = new BlackjackGame(inputView, inputParser, outputView, shuffleStrategy);
        blackjackGame.run();
    }
}
