import controller.BlackJackController;
import service.BlackjackCalculatorService;
import service.BlackjackResultService;
import view.InputView;
import view.OutputView;

public class main {

    public static void main(String[] args) {
        BlackJackController blackJackController = new BlackJackController(
                new InputView(),
                new OutputView(),
                new BlackjackResultService(),
                new BlackjackCalculatorService()
        );

        blackJackController.run();
    }
}
