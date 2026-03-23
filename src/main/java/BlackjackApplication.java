import controller.BlackjackGameRunner;
import view.InputView;
import view.OutputView;

public class BlackjackApplication {

    public static void main(String[] args) {
        BlackjackGameRunner blackjackGameRunner = new BlackjackGameRunner(new InputView(), new OutputView());
        blackjackGameRunner.start();
    }
}
