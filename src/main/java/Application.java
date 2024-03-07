import controller.BlackJackController;
import java.util.Scanner;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView(new Scanner(System.in));
        OutputView outputView = new OutputView();

        BlackJackController blackJackController = new BlackJackController(inputView, outputView);
        blackJackController.playGame();
    }
}
