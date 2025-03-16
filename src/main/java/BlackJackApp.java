import object.game.BlackJackManager;
import object.view.InputView;
import object.view.OutputView;

public class BlackJackApp {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        BlackJackManager blackJackManager = new BlackJackManager(inputView, outputView);

        try {
            blackJackManager.run();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
