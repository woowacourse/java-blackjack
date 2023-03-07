import blackjackcontroller.BlackjackController;
import deck.CardsGenerator;
import deck.ShuffledCardsGenerator;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        CardsGenerator cardsGenerator = new ShuffledCardsGenerator();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        BlackjackController blackjackController = new BlackjackController(inputView, outputView, cardsGenerator);

        blackjackController.run();
    }
}
