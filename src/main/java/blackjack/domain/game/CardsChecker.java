package blackjack.domain.game;

import java.util.List;

import blackjack.domain.card.Card;

@FunctionalInterface
public interface CardsChecker {
	void check(String name, List<Card> cards);
}
