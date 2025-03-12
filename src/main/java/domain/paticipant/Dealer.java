package domain.paticipant;

import java.util.List;

import domain.card.Card;
import domain.card.Score;
import domain.duel.DuelResult;

public class Dealer implements PickableCard {

	private final Participant participant;

	public Dealer() {
		this.participant = new Participant();
	}

	public Dealer(final Participant participant) {
		this.participant = participant;
	}

	public void addCards(final List<Card> cards) {
		participant.addCards(cards);
	}

	public boolean isPickCard(final Score bustScore, final Score dealerPickCardScoreMax) {
		final Score score = participant.calculateAllScore(bustScore);
		return score.isLessThan(dealerPickCardScoreMax) || score.equals(dealerPickCardScoreMax);
	}

	public Score calculateAllScore(final Score bustScore) {
		return participant.calculateAllScore(bustScore);
	}

	public boolean isBust(final Score bustScore) {
		return calculateAllScore(bustScore).isGreaterThan(bustScore);
	}

	public void writeDuelResult(final DuelResult duelResult) {
		participant.writeDuelResult(duelResult);
	}

	public Participant getParticipant() {
		return participant;
	}
}
