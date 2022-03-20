import controller.BlackJackController;
import service.BlackJackService;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        BlackJackController controller = new BlackJackController(new BlackJackService());
        requestUntilValid(controller::run);
    }

    private static void requestUntilValid(Runnable request) {
        boolean requestDoneSuccessful;
        do {
            requestDoneSuccessful = tryRequest(request);
        } while (!requestDoneSuccessful);
    }

    private static boolean tryRequest(Runnable request) {
        try {
            request.run();
            return true;
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception.getMessage());
            return false;
        }
    }
}
