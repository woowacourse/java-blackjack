import controller.BlackJackController;
import domain.Cards;
import domain.CardsInitializer;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        CardsInitializer cardsInitializer = new CardsInitializer();
        Cards deck = cardsInitializer.initialize();

        BlackJackController blackJackController = new BlackJackController(inputView, outputView, deck);

        blackJackController.run();
    }
}
