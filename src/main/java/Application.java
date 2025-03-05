import contoller.BlackjackController;
import domain.GameManager;

public class Application {

    public static void main(String[] args) {
        BlackjackController controller = new BlackjackController();
        controller.run();
    }
}
