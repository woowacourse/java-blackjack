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

	public boolean under21() {
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

	public Name getName() {
		return this.name;
	}

	ParticipantCards getCards() {
		return this.cards;
	}

	@Override
	public String toString() {
		return name.getName() + "카드: " + cards.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Player player = (Player)o;
		return Objects.equals(name, player.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

}
