package paticipant;

import java.util.List;

import card.Card;
import duel.DuelResult;
import game.GameScore;

public class Dealer {
	private static final GameScore DEALER_PICK_CARD_GAME_SCORE_MAX = GameScore.from(16);

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

	public boolean isPickCard() {
		final GameScore gameScore = participant.calculateAllScore();
		return gameScore.isLessThan(DEALER_PICK_CARD_GAME_SCORE_MAX) || gameScore.equals(
			DEALER_PICK_CARD_GAME_SCORE_MAX);
	}

	public GameScore calculateAllScore() {
		return participant.calculateAllScore();
	}

	public boolean isBust() {
		return participant.isBust();
	}

	public void writeDuelResult(final DuelResult duelResult) {
		participant.writeDuelResult(duelResult);
	}

	public Card getFirstCard() {
		return participant.getFirstCard();
	}

	public List<Card> getCards() {
		return participant.getCards();
	}

	public Participant getParticipant() {
		return participant;
	}
}
