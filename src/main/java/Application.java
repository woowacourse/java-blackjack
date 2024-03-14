import controller.BlackJackController;
import handler.RetryHandler;

public class Application {

    public static void main(String[] args) {
        RetryHandler retryHandler = new RetryHandler();
        BlackJackController blackJackController = new BlackJackController(retryHandler);
        blackJackController.start();
    }
}
