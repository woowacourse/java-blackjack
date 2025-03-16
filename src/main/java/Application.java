import controller.BlackJackGame;
import blackjack.view.ConsoleInputView;
import blackjack.view.ConsoleOutputView;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
    public static void main(String[] args) {

        InputView inputView = new ConsoleInputView();
        OutputView outputView = new ConsoleOutputView();

        BlackJackGame controller = new BlackJackGame(inputView, outputView);
        controller.run();
    }
}
