package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.stream.IntStream;

public class CardDivider {
	private static final int START_RANGE_INDEX = 0;
	private static final String EMPTY_CARDS_MESSAGE = "카드 덱이 비어있습니다.";

	private final List<Card> cards;
	private final Stack<Integer> orders;

	public CardDivider() {
		this.cards = CardFactory.create();
		this.orders = new Stack<>();
		IntStream.range(START_RANGE_INDEX, cards.size())
			.forEach(num -> orders.push(num));
		Collections.shuffle(orders);
	}

	public CardDivider(List<Integer> orders) {
		this.cards = CardFactory.create();
		this.orders = new Stack<>();
		orders.forEach(num -> this.orders.push(num));
	}

	public Card divide() {
		if (orders.isEmpty()) {
			throw new NoSuchElementException(EMPTY_CARDS_MESSAGE);
		}
		int order = orders.pop();
		return cards.get(order);
	}
}
