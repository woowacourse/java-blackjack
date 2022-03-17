package domain.participant;

import domain.card.Card;
import domain.card.Hand;

public class Participant {

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

	public boolean isBust() {
		return hand.isBust();
	}

	public boolean isMaxScore() {
		return hand.isMaxScore();
	}

	public int getBestScore() {
		return hand.getScore();
	}

	public boolean isBlackJack() {
		return hand.isBlackJack();
	}

	public Name getName() {
		return name;
	}

	public Hand getHand() {
		return Hand.copyOf(hand);
	}

	public Betting getBetting() {
		return betting;
	}
}
