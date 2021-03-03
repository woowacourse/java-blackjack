package blakcjack.domain.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Card {
	private static final Map<String, Card> cache = new HashMap<>();

	static {
		for (final CardSymbol cardSymbol : CardSymbol.values()) {
			for (final CardNumber cardNumber : CardNumber.values()) {
				final String key = generateKey(cardSymbol, cardNumber);
				final Card card = new Card(cardSymbol, cardNumber);
				cache.put(key, card);
			}
		}
	}

	private final CardSymbol cardSymbol;
	private final CardNumber cardNumber;

	private Card(final CardSymbol cardSymbol, final CardNumber cardNumber) {
		this.cardSymbol = cardSymbol;
		this.cardNumber = cardNumber;
	}

	public static Card of(final CardSymbol cardSymbol, final CardNumber cardNumber) {
		final String key = generateKey(cardSymbol, cardNumber);
		return Optional.ofNullable(cache.get(key))
				.orElseThrow(IllegalArgumentException::new);
	}

	private static String generateKey(CardSymbol cardSymbol, CardNumber cardNumber) {
		return cardSymbol.name() + cardNumber.name();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Card card = (Card) o;
		return cardSymbol == card.cardSymbol && cardNumber == card.cardNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardSymbol, cardNumber);
	}

	public CardNumber getCardNumber() {
		return cardNumber;
	}

	public CardSymbol getCardSymbol() {
		return cardSymbol;
	}
}
