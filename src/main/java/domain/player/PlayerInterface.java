package domain.player;

import domain.card.CardDeck;

public interface PlayerInterface {
	void cardDraw(CardDeck cardDeck, int count);

	String getName();

	String toString();
}
