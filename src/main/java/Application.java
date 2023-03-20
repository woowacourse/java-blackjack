import java.util.Scanner;

import controller.GameController;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);
        OutputView outputView = new OutputView();
        GameController gameController = new GameController(inputView, outputView);
        gameController.run();
        scanner.close();
    }

}
