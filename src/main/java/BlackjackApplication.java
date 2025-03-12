import blackjack.ConsoleBlackjackGame;
import view.InputView;
import view.OutputView;

public class BlackjackApplication {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ConsoleBlackjackGame consoleBlackjackGame = new ConsoleBlackjackGame(inputView, outputView);

        consoleBlackjackGame.run();
    }
}
