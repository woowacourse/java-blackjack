package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.service.BlackJackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Scanner;

public class BlackJackApplication {

	public static void main(String[] args) {
		InputView inputView = new InputView(new Scanner(System.in));
		OutputView outputView = new OutputView();
		BlackJackController controller = new BlackJackController(new BlackJackService());

		controller.run(inputView, outputView);
	}
}
