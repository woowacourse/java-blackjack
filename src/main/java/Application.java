import controller.BlackJackController;
import domain.BlackJackGame;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        BlackJackGame blackJackGame = BlackJackGame.create();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BlackJackController blackJackController = new BlackJackController(inputView, outputView, blackJackGame);

        blackJackController.run();
    }
}
