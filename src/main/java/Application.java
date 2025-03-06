import controller.BlackjackController;
import java.util.Base64;

public class Application {
    public static void main(String[] args) {
        BlackjackController controller = new BlackjackController();

        controller.run();
    }
}
