import controller.BlackJackController;
import domain.CardShuffleStrategy;
import domain.RandomCardShuffleStrategy;
import view.InputView;
import view.OutputView;

public class Main {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BlackJackController controller = new BlackJackController(inputView, outputView);

        CardShuffleStrategy strategy = new RandomCardShuffleStrategy();
        controller.doGame(strategy);
    }
}
