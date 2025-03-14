import game.BlackjackManager;
import view.InputView;
import view.OutputView;

public class BlackjackApp {

	public static void main(String[] args) {
		InputView inputView = new InputView();
		OutputView outputView = new OutputView();
		BlackjackManager blackjackManager = new BlackjackManager(inputView, outputView);

		blackjackManager.run();
	}
}
