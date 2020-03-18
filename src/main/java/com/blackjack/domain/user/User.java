package com.blackjack.domain.user;

import java.util.Collections;

import com.blackjack.domain.card.Card;

public abstract class User {
	final Hand hand;
	private final Name name;

	User(Name name) {
		this.hand = new Hand(Collections.emptyList());
		this.name = name;
	}

	public void draw(Card card) {
		hand.add(card);
	}

	public int calculateScore() {
		return hand.calculateScore();
	}

	public abstract boolean canDraw();

	public String getName() {
		return name.toString();
	}

	public Hand getHand() {
		return hand;
	}
}
