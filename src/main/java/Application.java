import controller.BlackJackGameController;
import view.InputView;
import view.ResultView;

public class Application {
    public static void main(String[] args) {
        BlackJackGameController blackJackGameController = new BlackJackGameController(new InputView(), new ResultView());
        blackJackGameController.gameStart();
    }
}
