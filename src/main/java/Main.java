import controller.BlackJackController;
import controller.InputController;
import model.Cards;
import service.BlackJackService;
import view.InputView;

public class Main {

    public static void main(String[] args) {
        BlackJackService blackJackService = new BlackJackService(new Cards());
        InputController inputController = new InputController(new InputView());
        BlackJackController blackJackController = new BlackJackController(inputController, blackJackService);

        blackJackController.run();
    }
}
