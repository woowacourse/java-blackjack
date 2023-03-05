import controller.BlackjackController;
import view.InputValidator;
import view.InputView;
import view.OutputView;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView(new Scanner(System.in), new InputValidator());
        OutputView outputView = new OutputView();

        BlackjackController controller = new BlackjackController(inputView, outputView);
        controller.play();
    }
}
