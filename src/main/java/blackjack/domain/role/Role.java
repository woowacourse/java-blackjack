package blackjack.domain.role;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public abstract class Role {

	protected final String name;
	protected final Hand hand;

	private boolean drawMore;

	public Role(final String name, final Hand hand) {
		this.name = name;
		this.hand = hand;
		this.drawMore = true;
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

	public String getName() {
		return name;
	}

	public Hand getHand() {
		return hand;
	}

	public List<String> getCardsInformation() {
		return hand.getCardsInformation();
	}

	public boolean wantDraw() {
		return drawMore;
	}
}
