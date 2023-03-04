import controller.GameController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import view.InputView;
import view.OutputView;

public class GameApplicationConfig {

    public GameController gameController() {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final InputView inputView = inputView(bufferedReader);
        final OutputView outputView = new OutputView();

        return new GameController(inputView, outputView);
    }

    private InputView inputView(final BufferedReader bufferedReader) {
        return new InputView(bufferedReader);
    }
}
