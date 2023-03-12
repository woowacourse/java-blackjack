package domain;

import java.util.List;

import domain.card.Card;

public class Player {

	private final String name;
	private final PlayerStatus status;

	public Player(final String name, final int bet) {
		this.name = name;
		this.status = new PlayerStatus(bet);
	}

	public void addCard(final Card card) {
		status.addCard(card);
	}

	public boolean isBust() {
		return status.isBust();
	}

	public boolean canReceiveCard() {
		return status.canReceiveCard();
	}

	public boolean isBlackJack() {
		return status.isBlackJack();
	}

	public String getName() {
		return name;
	}

	public List<Card> getCards() {
		return status.getCards();
	}

	public int getScore() {
		return status.getScore();
	}

	public int getBet() {
		return status.getBet();
	}
}
