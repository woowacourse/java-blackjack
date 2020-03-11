package com.blackjack.domain.user;

import java.util.ArrayList;
import java.util.List;

import com.blackjack.domain.Score;
import com.blackjack.domain.card.Card;

public class Hands {
	private List<Card> cards;

	public Hands(List<Card> cards) {
		cards = new ArrayList<>(cards);
	}

	void add(Card card) {
		cards.add(card);
	}

	Score calculateScore() {
		return new Score(0);
	}
}
