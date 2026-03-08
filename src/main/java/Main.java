import controller.BlackJackController;
import domain.CardCreationStrategy;
import domain.RandomCardCreationStrategy;
import view.InputView;
import view.OutputView;

public class Main {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        CardCreationStrategy strategy = new RandomCardCreationStrategy();

        new BlackJackController(inputView, outputView, strategy).doGame();
    }
}
