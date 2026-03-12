import controller.BlackJackController;
import controller.InputController;
import service.BlackJackService;

public class Main {

    public static void main(String[] args) {
        BlackJackService blackJackService = new BlackJackService();
        InputController inputController = new InputController();
        BlackJackController blackJackController = new BlackJackController(inputController, blackJackService);

        blackJackController.run();
    }
}
