import controller.BlackJackController;
import model.BlackJackDeck;
import service.BlackJackService;

public class Main {

    public static void main(String[] args) {
        BlackJackService blackJackService = new BlackJackService(new BlackJackDeck());
        BlackJackController blackJackController = new BlackJackController(blackJackService);

        blackJackController.run();
    }
}
