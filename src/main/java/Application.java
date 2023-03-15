import controller.BlackjackController;
import view.InputView;
import view.OutputView;

import java.util.Scanner;

public class Application {

    public static void main(final String[] args) {
        final BlackjackController controller = new BlackjackController(new InputView(new Scanner(System.in)), new OutputView());
        controller.run();
    }
}
