import controller.BlackjackGameController;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputview = new InputView();
        OutputView outputView = new OutputView();
        BlackjackGameController blackJackGameController = new BlackjackGameController(inputview, outputView);
        blackJackGameController.run();
    }
}
