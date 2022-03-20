package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Scanner;

public class BlackJackApplication {

	public static void main(String[] args) {
		InputView inputView = new InputView(new Scanner(System.in));
		OutputView outputView = new OutputView();
		BlackjackController controller = new BlackjackController(inputView, outputView);

		controller.run();
	}
}
