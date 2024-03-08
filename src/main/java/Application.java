import controller.Game;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        Game game = new Game(inputView, outputView);
        game.run();
    }
}
