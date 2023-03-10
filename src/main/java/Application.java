import controller.MainController;
import view.InputView;
import view.OutputView;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        
        InputView inputView = new InputView(new Scanner(System.in));
        OutputView outputView = new OutputView();
        MainController mainController = new MainController(inputView, outputView);

        mainController.run();
    }
}
