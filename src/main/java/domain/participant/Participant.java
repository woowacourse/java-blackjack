package domain.participant;

import java.util.Iterator;
import java.util.List;

import domain.card.Card;
import domain.participant.info.Betting;
import domain.participant.info.Hand;
import domain.participant.info.Name;

public class Participant {
	private static final int WINNING_SCORE = 21;
	private static final int ADDITIONAL_SCORE_ACE = 10;
	private static final int BLACKJACK_SIZE = 2;

	protected final Name name;
	protected final Hand hand;
	protected final Betting betting;

	public Participant(Name name, Hand hand, Betting betting) {
		this.name = name;
		this.hand = hand;
		this.betting = betting;
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public boolean isBlackJack() {
		return hand.size() == BLACKJACK_SIZE && getScore() == WINNING_SCORE;
	}

	public boolean isBust() {
		return sumPoint() > WINNING_SCORE;
	}

	public int getScore() {
		int score = sumPoint();

		if (hand.hasAce() && canAddAcePoint(score)) {
			score += ADDITIONAL_SCORE_ACE;
		}

		return score;
	}

	private boolean canAddAcePoint(int score) {
		return score + ADDITIONAL_SCORE_ACE <= WINNING_SCORE;
	}

	private int sumPoint() {
		int totalPoint = 0;
		Iterator<Card> iterator = hand.iterator();

		while (iterator.hasNext()) {
			Card card = iterator.next();
			totalPoint += card.getPoint();
		}
		return totalPoint;
	}

	public String getName() {
		return name.getName();
	}

	public List<Card> getCards() {
		return hand.getHand();
	}

	public int getBettingMoney() {
		return betting.getBettingMoney();
	}
}
