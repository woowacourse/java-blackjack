package blackjack;

import java.util.List;

public interface User {
	void append(Card card);

	List<Card> getCards();

	String getName();
}
