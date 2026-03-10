package config;

import controller.BlackjackController;
import java.util.Scanner;
import view.InputView;
import view.OutputView;

public class AppConfig {

    public BlackjackController controller() {
        return new BlackjackController(inputView(), outputView());
    }

    public InputView inputView() {
        return new InputView(new Scanner(System.in));
    }

    public OutputView outputView() {
        return new OutputView();
    }
}
