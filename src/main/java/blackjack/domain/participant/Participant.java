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
	protected BettingAmount battingAmount;

	private boolean drawMore;

	public Participant(final String name, final Hand hand, final BettingAmount bettingAmount) {
		this.name = name;
		this.hand = hand;
		this.battingAmount = bettingAmount;
		this.drawMore = true;
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
		battingAmount.giveOneAndHalfTime();
	}

	public boolean isBlackJack() {
		return hand.isBlackJack();
	}

	public int calculateFinalScore() {
		return hand.calculateScore();
	}

	public int calculateBettingResult() {
		return battingAmount.calculateIncome();
	}

	public void stopDraw() {
		drawMore = false;
	}

	public int getIncome() {
		return battingAmount.calculateIncome();
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

	public boolean wantDraw() {
		return drawMore;
	}

	public abstract boolean canDraw();

	public abstract int getDrawStandard();

	public abstract void getBattingAmountFrom(final Participant participant);

	protected int getCurrentIncome() {
		return battingAmount.getTotalValue();
	}

	protected void loseAll() {
		battingAmount.loseAll();
	}

	protected Outcome judgeCompeteResult(final int score, final int targetScore) {
		return Outcome.of(score, targetScore);
	}
}
