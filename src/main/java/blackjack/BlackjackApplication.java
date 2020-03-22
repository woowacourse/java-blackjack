package blackjack;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardFactory;
import blackjack.view.InputView;

import java.util.Scanner;

public class BlackjackApplication {
	public static void main(String[] args) {
		new BlackjackController(CardDeck.getInstance(new CardFactory()), new InputView(new Scanner(System.in))).run();
	}
}
