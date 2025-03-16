import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame(new InputView(), new OutputView());
        blackjackGame.play();
    }
}
