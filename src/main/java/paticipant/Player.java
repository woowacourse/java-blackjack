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

	public boolean isPickCard(final Score bustScore) {
		final Score score = participant.calculateAllScore(bustScore);
		return score.isLessThan(bustScore) || score.equals(bustScore);
	}

	public Score calculateAllScore(final Score bustScore) {
		return participant.calculateAllScore(bustScore);
	}

	public boolean isBust(final Score bustScore) {
		return participant.isBust(bustScore);
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
