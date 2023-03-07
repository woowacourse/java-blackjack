import controller.BlackJackController;
import model.card.Deck;
import model.card.RandomShuffleMaker;
import ui.input.InputView;
import ui.output.OutputView;

public class Application {

    public static void main(String[] args) {
        final BlackJackController blackJackController = new BlackJackController(new InputView(), new OutputView());
        blackJackController.init(Deck.create(new RandomShuffleMaker()));
    }
}
