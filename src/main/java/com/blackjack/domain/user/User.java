package com.blackjack.domain.user;

import java.util.Collections;

import com.blackjack.domain.ResultType;
import com.blackjack.domain.Score;
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
