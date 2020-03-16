package blackjack.card.domain;

import blackjack.card.domain.component.CardNumber;
import blackjack.card.domain.component.Symbol;

public class CardBundleHelper {
	public static CardBundle aCardBundle(CardNumber... cardNumbers) {
		CardBundle cardBundle = CardBundle.emptyBundle();
		for (CardNumber cardNumber : cardNumbers) {
			cardBundle.addCard(Card.of(Symbol.HEART, cardNumber));
		}
		return cardBundle;
	}
}
