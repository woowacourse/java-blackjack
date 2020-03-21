package domain.participant;

import java.util.Objects;

import domain.ParticipantCards;
import domain.card.Card;

public class Participant implements ParticipantInterface {
	private static final int MAX_SCORE = 21;

	private Name name;
	private ParticipantCards cards;
	private boolean isBlackJack = false;

	Participant(Name name) {
		this.name = name;
		this.cards = new ParticipantCards();
	}

	public void receive(Card card) {
		cards.add(card);
	}

	public boolean canHit() {
		return (this.calculateScore() < MAX_SCORE);
	}

	public void setBlackJack(int score) {
		if (score == 21) {
			this.isBlackJack = true;
		}
	}

	public int calculateScore() {
		return cards.calculateScore();
	}

	public boolean isBust() {
		return (this.calculateScore() > MAX_SCORE);
	}

	public boolean isBlackJack() {
		return isBlackJack;
	}

	public String getName() {
		return this.name.getName();
	}

	ParticipantCards getCards() {
		return this.cards;
	}

	@Override
	public String toString() {
		return name.getName() + "카드: " + cards.toString();
	}
}
