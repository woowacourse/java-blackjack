import blackjack.BlackjackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {

    public static void main(String[] args) {
        new BlackjackGame(
                new InputView(),
                new OutputView()
        ).play();
    }
}
