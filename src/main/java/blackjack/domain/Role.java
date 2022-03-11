package blackjack.domain;

import java.util.EnumMap;
import java.util.Map;

public abstract class Role {

	private static final int COMPETE_COUNT = 1;

	protected final String name;
	protected final Hand hand;
	protected final Map<Outcome, Integer> competeResult;

	public Role(final String name, final Hand hand) {
		this.name = name;
		this.hand = hand;
		this.competeResult = new EnumMap<>(Outcome.class);
	}

	public void draw(final Card card) {
		hand.addCard(card);
	}

	public int calculateFinalScore() {
		return hand.calculateOptimalScore();
	}

	public abstract boolean canDraw();

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
}
