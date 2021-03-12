package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
	private static final int FIRST_INDEX = 0;
	private static final String ERROR_MESSAGE_DECK_SOLD_OUT = "덱이 모두 소진되었습니다. 새로운 게임을 시작해주세요.";

	private final List<Card> deck;

	public Deck() {
		this.deck = makeCards();
	}

	private List<Card> makeCards() {
		return Arrays.stream(CardPattern.values())
			.flatMap(cardPattern -> Arrays.stream(CardNumber.values())
				.map(cardNumber -> new Card(cardPattern, cardNumber)))
			.collect(Collectors.toList());
	}

	public void shuffleCards() {
		Collections.shuffle(deck);
	}

	public Card dealCard() {
		if (deck.isEmpty()) {
			throw new IllegalArgumentException(ERROR_MESSAGE_DECK_SOLD_OUT);
		}
		return deck.remove(FIRST_INDEX);
	}
}
