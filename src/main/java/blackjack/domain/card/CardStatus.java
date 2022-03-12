package blackjack.domain.card;

import java.util.Arrays;
import java.util.function.Predicate;

import blackjack.domain.BlackJackGame;

public enum CardStatus {

	BLACKJACK(cards -> cards.size() == BlackJackGame.INIT_DISTRIBUTION_COUNT &&
		cards.sum() == BlackJackGame.MAX_CARD_VALUE),
	BUST(cards -> cards.sum() > BlackJackGame.MAX_CARD_VALUE),
	NORMAL(cards -> true);

	private final Predicate<Cards> predicate;

	CardStatus(Predicate<Cards> predicate) {
		this.predicate = predicate;
	}

	public static CardStatus of(Cards cards) {
		return Arrays.stream(values())
			.filter(status -> status.predicate.test(cards))
			.findAny()
			.orElseThrow();
	}

	public boolean isBlackJack() {
		return this == BLACKJACK;
	}

	public boolean isBust() {
		return this == BUST;
	}
}
