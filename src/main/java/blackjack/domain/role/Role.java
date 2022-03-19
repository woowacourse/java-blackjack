package blackjack.domain.role;

import java.util.List;

import blackjack.domain.BettingAmount;
import blackjack.domain.BlackJack;
import blackjack.domain.Outcome;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;

public abstract class Role {

	protected final String name;
	protected final Hand hand;

	private boolean drawMore;
	private BettingAmount bettingAmount;

	public Role(final String name, final Hand hand, final BettingAmount bettingAmount) {
		this.name = name;
		this.hand = hand;
		this.bettingAmount = bettingAmount;
		this.drawMore = true;
	}

	public void draw(final Deck deck, final int theNumberOfCars) {
		for (int i = 0; i < theNumberOfCars; i++) {
			hand.addCard(deck.draw());
		}
	}

	public void distributeBettingAmount(final Outcome outcome) {
		if (winWithoutBlackJack(outcome)) {
			bettingAmount.giveTwoTimes();
		}
	}

	private boolean winWithoutBlackJack(final Outcome outcome) {
		return outcome == Outcome.VICTORY && !hand.isBlackJack();
	}

	public void recordCompeteResult(final Outcome outcome, final Role player) {
		if (outcome == Outcome.VICTORY) {
			final int finalIncome = bettingAmount.getTotalValue() + player.getCurrentIncome();
			bettingAmount = new BettingAmount(finalIncome, bettingAmount.getInitialValue());
			player.loseAll();
		}
	}

	private int getCurrentIncome() {
		return bettingAmount.getTotalValue();
	}

	private void loseAll() {
		bettingAmount.loseAll();
	}

	public int getIncome() {
		return bettingAmount.calculateIncome();
	}

	public void earnAmountByBlackJack() {
		if (isBlackJack()) {
			return;
		}
		bettingAmount.giveOneAndHalfTime();
	}

	public boolean isBlackJack() {
		return calculateFinalScore() == BlackJack.OPTIMIZED_WINNING_NUMBER;
	}

	public int calculateFinalScore() {
		return hand.calculateOptimalScore();
	}

	public int calculateBettingResult() {
		return bettingAmount.calculateIncome();
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

	public List<String> getCardsName() {
		return hand.getCardsName();
	}

	public boolean wantDraw() {
		return drawMore;
	}

	public String calculateFinalResult() {
		return hand.getFinalScore();
	}

	public abstract boolean canDraw();

	public abstract int getDrawStandard();
}
