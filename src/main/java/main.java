import controller.BlackJackController;
import service.CalculatorService;
import service.ResultService;
import view.InputView;
import view.OutputView;

public class main {

    public static void main(String[] args) {
        BlackJackController blackJackController = new BlackJackController(
                new InputView(),
                new OutputView(),
                new ResultService(),
                new CalculatorService()
        );

        blackJackController.run();
    }
}
