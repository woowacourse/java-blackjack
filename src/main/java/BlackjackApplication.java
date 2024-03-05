import java.util.Scanner;

import controller.BlackjackController;
import domain.Players;
import view.InputView;

public class BlackjackApplication {
	public static void main(String[] args) {
		InputView inputView = new InputView(new Scanner(System.in));
		BlackjackController blackjackController = new BlackjackController(inputView);

		Players players = blackjackController.createPlayers();
	}
}
