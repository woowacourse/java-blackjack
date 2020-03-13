package com.blackjack.domain.user;

import com.blackjack.domain.card.CardDeck;

public interface Drawable {
	void drawAtFirst(CardDeck cardDeck);

	void draw(CardDeck cardDeck);

	boolean canDraw();
}
