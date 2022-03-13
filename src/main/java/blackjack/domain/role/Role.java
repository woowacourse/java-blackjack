package blackjack.domain.role;

import java.util.EnumMap;
import java.util.Map;

import blackjack.domain.Outcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public abstract class Role {

	private static final int COMPETE_COUNT = 1;

	protected final String name;
	protected final Hand hand;
	protected final Map<Outcome, Integer> competeResult;

	private boolean drawMore;

	public Role(final String name, final Hand hand) {
		this.name = name;
		this.hand = hand;
		this.drawMore = true;
		this.competeResult = new EnumMap<>(Outcome.class);
	}

	public void draw(final Card card, final int theNumberOfCars) {
		for (int i = 0; i < theNumberOfCars; i++) {
			hand.addCard(card);
		}
	}

	public int calculateFinalScore() {
		return hand.calculateOptimalScore();
	}

	public abstract boolean canDraw();

	public void stopDraw() {
		drawMore = false;
	}

	public void recordCompeteResult(Outcome outcome) {
		competeResult.merge(outcome, COMPETE_COUNT, Integer::sum);
	}

	public String getName() {
		return name;
	}

	public Hand getHand() {
		return hand;
	}

	public Map<Outcome, Integer> getCompeteResult() {
		return new EnumMap<>(competeResult);
	}

	public boolean wantDraw() {
		return drawMore;
	}
}
