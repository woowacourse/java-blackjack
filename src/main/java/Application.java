import controller.BlackjackGame;
import util.InputParser;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        InputParser inputParser = new InputParser();
        OutputView outputView = new OutputView();

        BlackjackGame blackjackGame = new BlackjackGame(inputView, inputParser, outputView);
        blackjackGame.run();
    }
}
