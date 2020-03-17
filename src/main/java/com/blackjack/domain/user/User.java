package com.blackjack.domain.user;

import java.util.Collections;
import java.util.Objects;

import com.blackjack.domain.ResultType;
import com.blackjack.domain.Score;
import com.blackjack.domain.card.Card;
import com.blackjack.domain.card.CardDeck;

public abstract class User {
	private static final int FIRST_DRAW_COUNT = 2;

	final Hands hands;
	private final Name name;

	User(Name name) {
		validateNameIsNotNull(name);
		this.name = name;
		this.hands = new Hands(Collections.emptyList());
	}

	private void validateNameIsNotNull(Name name) {
		if (Objects.isNull(name)) {
			throw new IllegalArgumentException("이름이 존재하지 않습니다.");
		}
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

	public ResultType compareScoreWith(User user) {
		Score score = hands.calculateScore();
		Score dealerScore = user.hands.calculateScore();
		return ResultType.of(score.compareTo(dealerScore));
	}

	public abstract boolean canDraw();

	public String getName() {
		return name.toString();
	}

	public Hands getHands() {
		return hands;
	}
}
