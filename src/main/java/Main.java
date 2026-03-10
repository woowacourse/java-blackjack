import controller.BlackJackController;
import controller.InputController;
import model.BlackJackDeck;
import model.CardDeckFactory;
import service.BlackJackService;

public class Main {

    public static void main(String[] args) {
        BlackJackService blackJackService = new BlackJackService(new BlackJackDeck(CardDeckFactory.createShuffledDeck()));
        InputController inputController = new InputController();
        BlackJackController blackJackController = new BlackJackController(inputController, blackJackService);

        blackJackController.run();
    }
}
