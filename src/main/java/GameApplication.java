import controller.GameController;
import view.InputView;
import view.OutputView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public final class GameApplication {
    public static void main(String[] args) {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final InputView inputView = new InputView(bufferedReader);
        final OutputView outputView = new OutputView();

        final GameController gameController = new GameController(inputView, outputView);
        gameController.start();
    }
}
