package blackjack.domain.card;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Card {
	private static final String INVALID_CARD_EXCEPTION = "존재하지 않는 카드입니다.";
	private static final List<Card> CACHED_CARDS;

	static {
		CACHED_CARDS = setUpCards();
	}

	private static List<Card> setUpCards() {
		return Stream.of(Denomination.values())
			.flatMap(Card::setUpCardSuitByDenomination)
			.collect(Collectors.toList());
	}

	private static Stream<Card> setUpCardSuitByDenomination(final Denomination denomination) {
		return Stream.of(Suit.values())
			.map(suit -> new Card(denomination, suit));
	}

	private final Denomination denomination;
	private final Suit suit;

	private Card(final Denomination denomination, final Suit suit) {
		this.denomination = denomination;
		this.suit = suit;
	}

	public static Card of(final Denomination denomination, final Suit suit) {
		return CACHED_CARDS.stream()
			.filter(card -> card.denomination == denomination)
			.filter(card -> card.suit == suit)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(INVALID_CARD_EXCEPTION));
	}

	public static List<Card> getCachedCards() {
		return CACHED_CARDS;
	}

	public String getSuit() {
		return suit.getValue();
	}

	public int getScore() {
		return this.denomination.getValue();
	}

	public boolean isAce() {
		return denomination == Denomination.ACE;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Card card = (Card) o;
		return denomination == card.denomination && suit == card.suit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(denomination, suit);
	}
}
