package domain;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.Cards;

public class PlayerStatus {

	private static final int INITIAL_CARD_COUNT = 2;

	private final Cards cards;
	private final int bet;
	private Score score;
	private boolean blackJack;

	public PlayerStatus(int bet) {
		this.cards = Cards.empty();
		this.bet = bet;
		this.blackJack = false;
	}

	public void addCard(final Card card) {
		cards.add(card);
		score = Score.of(cards);
		if (cards.size() == INITIAL_CARD_COUNT) {
			blackJack = score.isBlackJackScore();
		}
	}

	public boolean isBust() {
		return score.isBust();
	}

	public boolean canReceiveCard() {
		return !score.isBust();
	}

	public boolean isBlackJack() {
		return blackJack;
	}

	public List<Card> getCards() {
		return new ArrayList<>(cards.getCards());
	}

	public int getScore() {
		return score.getValue();
	}

	public int getBet() {
		return bet;
	}
}
