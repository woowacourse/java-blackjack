import controller.BlackJackController;
import view.InputView;

public class Application {
	public static void main(String[] args) {
		new BlackJackController(InputView.inputNames()).run();
	}
}
