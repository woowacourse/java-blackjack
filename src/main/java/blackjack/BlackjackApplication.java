package blackjack;

import java.util.Scanner;

import blackjack.card.CardFactory;
import blackjack.view.InputView;

public class BlackjackApplication {
	public static void main(String[] args) {
		new BlackjackController(CardFactory.getInstance(), new InputView(new Scanner(System.in))).run();
	}
}
