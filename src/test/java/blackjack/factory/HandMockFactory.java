package blackjack.factory;

import blackjack.domain.role.Hand;
import blackjack.util.CreateHand;

public class HandMockFactory {
	
	public static Hand getBlackJackHand() {
		return CreateHand.create(CardMockFactory.of("A클로버"), CardMockFactory.of("K클로버"));
	}

	public static Hand getNotBlackjackTopHand() {
		return CreateHand.create(CardMockFactory.of("2클로버"),
				CardMockFactory.of("K클로버"),
				CardMockFactory.of("9클로버"));
	}

	public static Hand getBottomHand() {
		return CreateHand.create(CardMockFactory.of("2클로버"), CardMockFactory.of("3클로버"));
	}

	public static Hand getBustHand() {
		return CreateHand.create(CardMockFactory.of("K클로버"), CardMockFactory.of("J클로버"), CardMockFactory.of("Q클로버"));
	}
}
