import domain.BlackJackGameManager;
import view.InputView;
import view.OutputView;

public class BlackJackApp {
    public static void main(String[] args) {
        final BlackJackGameManager blackJackGameManager = new BlackJackGameManager(new InputView(), new OutputView());
        blackJackGameManager.start();
    }
}
