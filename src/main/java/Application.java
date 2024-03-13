import controller.Game;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        List<String> names = inputView.enterPlayerNames();
        OutputView outputView = new OutputView();

        Game game = new Game(names);
        game.readyGame(outputView);
        game.playGame(inputView, outputView);
        game.printDealerDrawMessage(outputView);
        game.printResult(outputView);
    }
}
