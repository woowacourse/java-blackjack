import game.BlackJackGame;
import io.InputView;
import io.OutputView;

public class Application {
    public static void main(String[] args) {

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        BlackJackGame game = new BlackJackGame(inputView, outputView);
        game.play();
    }
}
