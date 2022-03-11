import controller.BlackJackController;
import service.BlackJackService;

public class Application {

    public static void main(String[] args) {
        BlackJackController controller = new BlackJackController(new BlackJackService());
        controller.initGame();
        controller.hitPlayers();
        controller.hitDealer();
        controller.getCardsResults();
        controller.match();
    }
}
