import controller.BlackjackController;
import domain.game.Blackjack;
import view.InputView;
import view.OutputView;

public class Application {

	public static void main(String[] args) {
		InputView inputView = new InputView();
		OutputView outputView = new OutputView();
		Blackjack blackjack = new Blackjack();
		BlackjackController blackjackController = new BlackjackController(inputView, outputView, blackjack);

		blackjackController.run();
	}
}
