import domain.BlackjackGame;
import view.InputView;
import view.ResultView;

public class Main {
    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController(new InputView(), new ResultView(),
                new BlackjackGame());
        blackjackController.run();
    }
}
