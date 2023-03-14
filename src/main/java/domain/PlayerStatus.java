package domain;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

public class PlayerStatus {

	private final ScoreByCards scoreByCards;
	private final int bet;

	public PlayerStatus(int bet) {
		this.bet = bet;
		this.scoreByCards = ScoreByCards.empty();
	}

	public void addCard(final Card card) {
		scoreByCards.add(card);
	}

	public boolean isBust() {
		return scoreByCards.isBust();
	}

	public boolean canReceiveCard() {
		return !scoreByCards.isBust();
	}

	public boolean isBlackJack() {
		return scoreByCards.isBlackJack();
	}

	public int getScore() {
		return scoreByCards.getScore();
	}

	public List<Card> getCards() {
		return new ArrayList<>(scoreByCards.getCards());
	}

	public int getBet() {
		return bet;
	}
}
