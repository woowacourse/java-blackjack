package blackjack.card;

import blackjack.card.component.CardNumber;
import blackjack.card.component.Symbol;

public class CardBundleHelper {
	public static CardBundle aCardBundle(CardNumber... cardNumbers) {
		CardBundle cardBundle = new CardBundle();
		for (CardNumber cardNumber : cardNumbers) {
			cardBundle.addCard(Card.of(Symbol.HEART, cardNumber));
		}
		return cardBundle;
	}
}
