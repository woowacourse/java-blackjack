import controller.GameController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import view.InputView;
import view.OutputView;

public class GameApplication {

    public static void main(String[] args) {
        final InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        final InputView inputView = new InputView(bufferedReader);
        final OutputView outputView = new OutputView();

        final GameController gameController = new GameController(inputView, outputView);
        gameController.start();
    }
}
