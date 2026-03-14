import controller.BlackjackGame;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        BlackjackGame blackjackGame = new BlackjackGame(inputView, outputView);
        blackjackGame.run();
    }
}
