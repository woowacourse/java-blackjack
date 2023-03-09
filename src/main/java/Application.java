import controller.BlackJackController;
import domain.card.shuffler.RandomCardsShuffler;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        BlackJackController blackJackController = new BlackJackController(InputView.getInstance(), OutputView.getInstance());
        blackJackController.run(new RandomCardsShuffler());
    }
}
