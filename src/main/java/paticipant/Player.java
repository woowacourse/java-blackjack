package paticipant;

import java.util.List;

import card.Card;
import duel.DuelResult;
import value.Score;

public class Player {
	private final String name;
	private final Participant participant;

	public Player(final String name) {
		this.participant = new Participant();
		this.name = name;
	}

	public Player(final String name, final Participant participant) {
		this.name = name;
		this.participant = participant;
	}

	public void addCards(final List<Card> cards) {
		participant.addCards(cards);
	}

	public boolean isPickCard() {
		return !participant.isBust();
	}

	public Score calculateAllScore() {
		return participant.calculateAllScore();
	}

	public boolean isBust() {
		return participant.isBust();
	}

	public void writeDuelResult(final DuelResult duelResult) {
		participant.writeDuelResult(duelResult);
	}

	public String getName() {
		return name;
	}

	public Participant getParticipant() {
		return participant;
	}
}
