package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class CardDivider {
	private final List<Card> cards;
	private final Stack<Integer> orders;

	public CardDivider() {
		this.cards = CardFactory.create();
		this.orders = new Stack<>();
		for (int i = 0; i < 52; i++) {
			orders.push(i);
		}
		Collections.shuffle(orders);
	}

	public CardDivider(List<Integer> orders) {
		this.cards = CardFactory.create();
		this.orders = new Stack<>();
		for (Integer order : orders) {
			this.orders.push(order);
		}
	}

	public Card divide() {
		if (orders.isEmpty()) {
			throw new NoSuchElementException("카드 덱이 비어있습니다.");
		}
		Integer pop = orders.pop();
		return cards.get(pop);
	}
}
