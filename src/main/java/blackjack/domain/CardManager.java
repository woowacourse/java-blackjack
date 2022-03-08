package blackjack.domain;

import java.util.List;

import blackjack.domain.card.Card;

public interface CardManager {

	void addCard(Card card);

	List<Card> getCards();

	boolean isOverThan(int number);
}
