import controller.BlackJackController;
import domain.Result;
import service.BlackJackService;

public class Main {
    public static void main(String[] args) {
        Result result = new Result();
        BlackJackService blackJackService = new BlackJackService(result);
        BlackJackController blackJackController = new BlackJackController(blackJackService, result);
        blackJackController.run();
    }
}
