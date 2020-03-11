package com.blackjack.domain.user;

import java.util.Collections;

import com.blackjack.domain.card.Card;
import com.blackjack.domain.card.CardDeck;

public abstract class User {
	final Hands hands;

	User() {
		this.hands = new Hands(Collections.emptyList());
	}

	public void draw(CardDeck cardDeck) {
		Card card = cardDeck.pop();
		hands.add(card);
	}

	public abstract boolean canDraw();
}
