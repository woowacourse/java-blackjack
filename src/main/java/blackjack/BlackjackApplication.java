package blackjack;

import java.util.Scanner;

import blackjack.player.card.CardFactory;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackApplication {
	public static void main(String[] args) {
		new BlackjackGame(CardFactory.getInstance(), new InputView(new Scanner(System.in)), new OutputView()).run();
	}
}
