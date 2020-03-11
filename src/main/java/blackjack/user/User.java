package blackjack.user;

import blackjack.card.Card;

import java.util.List;

public interface User {
	void append(Card card);

	List<Card> getCards();

	String getName();
}
