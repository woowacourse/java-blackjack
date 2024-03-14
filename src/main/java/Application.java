import controller.BlackJackController;
import domain.CardShuffleStrategy;
import domain.RandomCardShuffleStrategy;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        CardShuffleStrategy cardShuffleStrategy = new RandomCardShuffleStrategy();
        BlackJackController blackJackController = new BlackJackController(inputView, outputView, cardShuffleStrategy);
        blackJackController.run();
    }
}
