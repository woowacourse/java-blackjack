import controller.BlackJackController;
import controller.InputController;
import model.BlackJackDeck;
import model.CardFactory;
import service.BlackJackService;

public class Main {

    public static void main(String[] args) {
        BlackJackService blackJackService = new BlackJackService(
                new BlackJackDeck(CardFactory.createShuffledCards()));
        InputController inputController = new InputController();
        BlackJackController blackJackController = new BlackJackController(inputController, blackJackService);

        blackJackController.run();
    }
}
