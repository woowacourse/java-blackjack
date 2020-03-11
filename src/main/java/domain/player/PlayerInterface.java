package domain.player;


import domain.card.cardfactory.Card;

import java.util.List;

public interface PlayerInterface {
	void cardDraw(List<Card> cards);

	int calculateScore();

	String getName();

	String toStringAllCard();
}
