package blackjack.domain.participant;

import java.util.List;

import blackjack.domain.BettingAmount;
import blackjack.domain.BlackJack;
import blackjack.domain.Outcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;

public abstract class Participant {

	protected static final String METHOD_ERROR = "메서드를 사용할 수 없습니다.";

	protected final String name;
	protected final Hand hand;
	protected BettingAmount bettingAmount;

	private boolean drawMore;

	public Participant(final String name, final Hand hand, final BettingAmount bettingAmount) {
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

	public int getIncome() {
		return bettingAmount.calculateIncome();
	}

	public void earnAmountByBlackJack() {
		if (!isBlackJack()) {
			return;
		}
		bettingAmount.giveOneAndHalfTime();
	}

	public boolean isBlackJack() {
		return calculateFinalScore() == BlackJack.OPTIMIZED_WINNING_NUMBER;
	}

	public int calculateFinalScore() {
		return hand.calculateScore();
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

	public List<Card> getCards() {
		return hand.getCards();
	}

	public Hand getHand() {
		return new Hand(hand.getCards());
	}

	public boolean wantDraw() {
		return drawMore;
	}

	public abstract boolean canDraw();

	public abstract int getDrawStandard();

	public abstract void distributeBettingAmount(final Outcome outcome);

	public abstract void distributeBettingAmount(final Outcome outcome, final Participant role);

	protected int getCurrentIncome() {
		return bettingAmount.getTotalValue();
	}

	protected void loseAll() {
		bettingAmount.loseAll();
	}
}
