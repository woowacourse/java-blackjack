import controller.BlackJackController;
import view.InputView;
import view.ResultView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        BlackJackController blackJackGame = new BlackJackController(inputView, resultView);
        blackJackGame.run();
    }
}
