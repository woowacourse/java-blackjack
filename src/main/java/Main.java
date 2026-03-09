import controller.BlackJackController;
import service.BlackJackService;

public class Main {
    public static void main(String[] args) {
        BlackJackService blackJackService = new BlackJackService();
        BlackJackController blackJackController = new BlackJackController(blackJackService);
        blackJackController.run();
    }
}
