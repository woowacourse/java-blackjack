package com.blackjack.domain.user;

import java.util.Collections;
import java.util.Objects;

import com.blackjack.domain.Score;
import com.blackjack.domain.card.Card;

public abstract class User {
	final Hand hand;
	private final Name name;

	User(Name name) {
		validateNameIsNotNull(name);
		this.name = name;
		this.hand = new Hand(Collections.emptyList());
	}

	private void validateNameIsNotNull(Name name) {
		if (Objects.isNull(name)) {
			throw new IllegalArgumentException("이름이 존재하지 않습니다.");
		}
	}

	public Score calculateHand() {
		return hand.calculate();
	}

	public void draw(Card card) {
		hand.add(card);
	}

	public abstract boolean canDraw();

	public String getName() {
		return name.toString();
	}

	public Hand getHand() {
		return hand;
	}
}
