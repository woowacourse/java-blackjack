package com.blackjack.domain.user;

import java.util.Collections;

import com.blackjack.domain.card.Card;
import com.blackjack.domain.card.CardDeck;

public abstract class User {
	private static final int FIRST_DRAW_COUNT = 2;

	final Hands hands;

	User() {
		this.hands = new Hands(Collections.emptyList());
	}

	public void draw(CardDeck cardDeck) {
		Card card = cardDeck.pop();
		hands.add(card);
	}

	public void drawAtFirst(CardDeck cardDeck) {
		for (int count = 0; count < FIRST_DRAW_COUNT; count++) {
			draw(cardDeck);
		}
	}

	public abstract boolean canDraw();
}
