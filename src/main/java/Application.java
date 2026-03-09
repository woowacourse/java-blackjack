import controller.BlackJackController;
import service.GameManager;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        BlackJackController blackJackController = new BlackJackController(new InputView(), new OutputView(), new GameManager());
        blackJackController.playGame();
    }
}
