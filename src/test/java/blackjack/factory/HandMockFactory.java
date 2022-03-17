package blackjack.factory;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.NINE;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.THREE;
import static blackjack.domain.card.Denomination.TWO;

import blackjack.domain.role.Hand;
import blackjack.util.CreateHand;

public class HandMockFactory {

	public static Hand getBlackJackHand() {
		return CreateHand.create(CardMockFactory.of(ACE), CardMockFactory.of(JACK));
	}

	public static Hand getNotBlackjackTopHand() {
		return CreateHand.create(CardMockFactory.of(TWO),
				CardMockFactory.of(KING),
				CardMockFactory.of(NINE));
	}

	public static Hand getBottomHand() {
		return CreateHand.create(CardMockFactory.of(TWO), CardMockFactory.of(THREE));
	}

	public static Hand getBustHand() {
		return CreateHand.create(CardMockFactory.of(KING), CardMockFactory.of(JACK),
				CardMockFactory.of(QUEEN));
	}
}
