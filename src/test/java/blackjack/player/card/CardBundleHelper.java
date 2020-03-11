package blackjack.player.card;

import blackjack.player.card.component.CardNumber;
import blackjack.player.card.component.Symbol;

public class CardBundleHelper {
	public static CardBundle aCardBundle(CardNumber... cardNumbers) {
		CardBundle cardBundle = new CardBundle();
		for (CardNumber cardNumber : cardNumbers) {
			cardBundle.addCard(new Card(Symbol.HEART, cardNumber));
		}
		return cardBundle;
	}
}
