package domain.player;

import domain.card.cardfactory.Card;

import java.util.List;

public interface Gamer {
	void cardDraw(List<Card> cards);

	int calculateScore();

	int calculateBurstIsZeroScore();

	boolean isBlackJack();

	boolean isNotBlackJack();
}
