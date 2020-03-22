package blackjack.domain.card;

import blackjack.domain.card.component.CardNumber;
import blackjack.domain.card.component.Symbol;

public class CardBundleHelper {
	public static CardBundle aCardBundle(CardNumber... cardNumbers) {
		CardBundle cardBundle = CardBundle.emptyBundle();
		for (CardNumber cardNumber : cardNumbers) {
			cardBundle.addCard(Card.of(Symbol.HEART, cardNumber));
		}
		return cardBundle;
	}
}
