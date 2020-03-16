package blackjack;

import java.util.Scanner;

import blackjack.card.domain.Card;
import blackjack.card.domain.CardDeck;
import blackjack.view.InputView;

public class BlackjackApplication {
	public static void main(String[] args) {
		new BlackjackController(
			CardDeck.getInstance(Card.getAllCards())
			, new InputView(new Scanner(System.in))
		).run();
	}
}
