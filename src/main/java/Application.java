import controller.BlackjackController;
import service.BlackjackService;

public class Application {
	public static void main(String[] args) {
		BlackjackController controller = new BlackjackController(new BlackjackService());
		controller.run();
	}
}
