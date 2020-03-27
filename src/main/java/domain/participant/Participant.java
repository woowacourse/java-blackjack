package domain.participant;

import domain.ParticipantCards;
import domain.card.Card;

public class Participant implements ParticipantInterface {
	private static final int MAX_SCORE = 21;
	public static final int BLACKJACK_SCORE = 21;
	public static final int INITIAL_CARDS_NUMBER = 2;

	private Name name;
	private ParticipantCards cards;

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

	public boolean isBust() {
		return (this.calculateScore() > MAX_SCORE);
	}

	public boolean isBlackJack() {
		return cards.calculateScore() == BLACKJACK_SCORE && cards.getSize() == INITIAL_CARDS_NUMBER;
	}

	public int calculateScore() {
		return cards.calculateScore();
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
