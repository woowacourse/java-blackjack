package com.blackjack.domain.user;

import java.util.Collections;

import com.blackjack.domain.ResultType;
import com.blackjack.domain.Score;
import com.blackjack.domain.card.Card;
import com.blackjack.domain.card.CardDeck;

public abstract class User {
	public static final int FIRST_DRAW_COUNT = 2;

	private final Name name;
	final Hand hand;

	User(Name name) {
		this.hand = new Hand(Collections.emptyList());
		this.name = name;
	}

	public void drawAtFirst(CardDeck cardDeck) {
		for (int count = 0; count < FIRST_DRAW_COUNT; count++) {
			draw(cardDeck);
		}
	}

	public void draw(CardDeck cardDeck) {
		Card card = cardDeck.pop();
		hand.add(card);
	}

	public ResultType compareScoreTo(User user) {
		Score score = calculateScore();
		Score userScore = user.calculateScore();
		return ResultType.of(score.compareTo(userScore));
	}

	public Score calculateScore() {
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
