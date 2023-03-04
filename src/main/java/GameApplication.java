import controller.GameController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import view.InputView;
import view.OutputView;

public class GameApplication {
    public static void main(String[] args) {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final InputView inputView = new InputView(bufferedReader);
        final OutputView outputView = new OutputView();

        final GameController gamePrepareController = new GameController(inputView, outputView);
        gamePrepareController.start();
    }
}
