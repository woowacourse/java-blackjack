package com.blackjack.domain.user;

import java.util.Collections;
import java.util.List;

import com.blackjack.domain.card.Card;
import com.blackjack.domain.card.CardDeck;

public abstract class User {
	private static final int FIRST_DRAW_COUNT = 2;

	private final Name name;
	final Hands hands;

	User(Name name) {
		this.hands = new Hands(Collections.emptyList());
		this.name = name;
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

	public String getName() {
		return name.toString();
	}

	public List<Card> getHands() {
		return hands.getCards();
	}
}
