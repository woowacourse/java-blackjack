package blackjack.domain.participant;

import java.util.List;

import blackjack.domain.BettingAmount;
import blackjack.domain.Outcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;

public abstract class Participant {

	protected final String name;
	protected final Hand hand;
	protected BettingAmount bettingAmount;

	public Participant(final String name, final Hand hand, final BettingAmount bettingAmount) {
		this.name = name;
		this.hand = hand;
		this.bettingAmount = bettingAmount;
	}

	public void draw(final Deck deck, final int theNumberOfCars) {
		for (int i = 0; i < theNumberOfCars; i++) {
			hand.addCard(deck.draw());
		}
	}

	public void earnAmountByBlackJack() {
		if (!isBlackJack()) {
			return;
		}
		bettingAmount.giveOneAndHalfTime();
	}

	public boolean isBlackJack() {
		return hand.isBlackJack();
	}

	public int calculateFinalScore() {
		return hand.calculateScore();
	}

	public int calculateBettingResult() {
		return bettingAmount.calculateIncome();
	}

	public int getIncome() {
		return bettingAmount.calculateIncome();
	}

	public List<Card> getCards() {
		return hand.getCards();
	}

	public Hand getHand() {
		return new Hand(hand.getCards());
	}

	public String getName() {
		return name;
	}

	public abstract boolean canDraw();

	public abstract void stopDraw();

	public abstract boolean selectToDrawMore();

	public abstract int getDrawStandard();

	public abstract void getBettingAmountFrom(final Participant participant);

	protected int getCurrentIncome() {
		return bettingAmount.getTotalValue();
	}

	protected void loseAll() {
		bettingAmount.loseAll();
	}

	protected Outcome judgeCompeteResult(final int score, final int targetScore) {
		return Outcome.of(score, targetScore);
	}
}
