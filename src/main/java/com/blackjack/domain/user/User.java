package com.blackjack.domain.user;

import java.util.Collections;

import com.blackjack.domain.ResultType;
import com.blackjack.domain.Score;
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

	public void drawAtFirst(CardDeck cardDeck) {
		for (int count = 0; count < FIRST_DRAW_COUNT; count++) {
			draw(cardDeck);
		}
	}

	public void draw(CardDeck cardDeck) {
		Card card = cardDeck.pop();
		hands.add(card);
	}

	public ResultType compareTo(User user) {
		Score score = hands.calculateScore();
		Score userScore = user.hands.calculateScore();
		return ResultType.of(score.compareTo(userScore));
	}

	public abstract boolean canDraw();

	public String getName() {
		return name.toString();
	}

	public Hands getHands() {
		return hands;
	}
}
