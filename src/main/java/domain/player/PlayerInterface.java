package domain.player;

import domain.card.CardDeck;

public interface PlayerInterface {
	void cardDraw(CardDeck cardDeck, int count);

	void cardDraw(CardDeck cardDeck);

	int calculateScore();

	String getName();

	String toStringAllCard();
}
