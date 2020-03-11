package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDivider {
	private final List<Card> cards;
	private final List<Integer> orders;

	public CardDivider() {
		this.cards = CardFactory.create();
		orders = new ArrayList<>();
		for (int index = 0; index < 52; index++) {
			orders.add(index);
		}
		Collections.shuffle(orders);
	}

	public CardDivider(List<Integer> orders) {
		this.cards = CardFactory.create();
		this.orders = orders;
	}

	public boolean divide() {
		return true;

	}
}
