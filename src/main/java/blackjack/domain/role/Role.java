package blackjack.domain.role;

import java.util.List;

import blackjack.domain.BattingAmount;
import blackjack.domain.BlackJack;
import blackjack.domain.Outcome;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;

public abstract class Role {

	protected final String name;
	protected final Hand hand;

	private boolean drawMore;
	private BattingAmount battingAmount;

	public Role(final String name, final Hand hand, final BattingAmount battingAmount) {
		this.name = name;
		this.hand = hand;
		this.battingAmount = battingAmount;
		this.drawMore = true;
	}

	public void draw(final Deck deck, final int theNumberOfCars) {
		for (int i = 0; i < theNumberOfCars; i++) {
			hand.addCard(deck.draw());
		}
	}

	public void distributeBettingAmount(final Outcome outcome) {
		if (winWithoutBlackJack(outcome)) {
			battingAmount.giveTwoTimes();
		}
	}

	private boolean winWithoutBlackJack(final Outcome outcome) {
		return outcome == Outcome.VICTORY && !hand.isBlackJack();
	}

	public void recordCompeteResult(final Outcome outcome, final Role player) {
		if (outcome == Outcome.VICTORY) {
			final int finalIncome = battingAmount.getFinalValue() + player.getCurrentIncome();
			battingAmount = new BattingAmount(finalIncome, battingAmount.getInitialValue());
			player.loseAll();
		}
	}

	private void loseAll() {
		battingAmount.loseAll();
	}

	private int getCurrentIncome() {
		return battingAmount.getFinalValue();
	}

	public int getIncome() {
		return battingAmount.calculateIncome();
	}

	public int calculateFinalScore() {
		return hand.calculateOptimalScore();
	}

	public abstract boolean canDraw();

	public abstract int getDrawStandard();

	public void earnAmountByBlackJack() {
		if (calculateFinalScore() != BlackJack.OPTIMIZED_WINNING_NUMBER) {
			return;
		}
		battingAmount.giveOneAndHalfTime();
	}

	public int calculateBettingResult() {
		return battingAmount.calculateIncome();
	}

	public void stopDraw() {
		drawMore = false;
	}

	public String getName() {
		return name;
	}

	public Hand getHand() {
		return new Hand(hand.getCards());
	}

	public List<String> getCardsInformation() {
		return hand.getCardsInformation();
	}

	public boolean wantDraw() {
		return drawMore;
	}

	public String calculateFinalResult() {
		return hand.getFinalScore();
	}
}
