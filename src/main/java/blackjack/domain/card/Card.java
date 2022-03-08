package blackjack.domain.card;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Card {

	private final CardShape cardShape;
	private final CardNumber cardNumber;

	private final static List<Card> cards = new LinkedList<>();

	static {
		for (CardShape shape : CardShape.values()) {
			addCards(shape);
		}
	}

	private static void addCards(CardShape shape) {
		for (CardNumber number : CardNumber.values()) {
			cards.add(new Card(shape, number));
		}
	}

	private Card(CardShape cardShape, CardNumber cardNumber) {
		this.cardShape = cardShape;
		this.cardNumber = cardNumber;
	}

	public static Card getInstance(CardShape shape, CardNumber number) {
		return cards.stream()
			.filter(card -> card.isSame(shape, number))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카드입니다."));
	}

	private boolean isSame(CardShape shape, CardNumber number) {
		return cardShape == shape && cardNumber == number;
	}

	public boolean isNotAce() {
		return !cardNumber.equals(CardNumber.ACE);
	}

	public static List<Card> getCards() {
		return new LinkedList<>(cards);
	}

	public int getNumber() {
		return cardNumber.getValue();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Card card = (Card)o;
		return cardShape == card.cardShape && cardNumber == card.cardNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardShape, cardNumber);
	}
}
