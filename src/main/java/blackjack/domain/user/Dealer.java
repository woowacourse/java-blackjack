package blackjack.domain.user;

public class Dealer extends Gamer {
	private static final String DEALER_NAME = "딜러";

	public Dealer() {
		super(DEALER_NAME);
	}

	public boolean hasHigherScore(final Gamer gamer) {
		return this.cards.hasHigherScore(gamer.cards);
	}

	public boolean hasEqualScore(final Gamer gamer) {
		return this.cards.isEqualScoreWith(gamer.cards);
	}

	public boolean isHit() {
		return this.cards.isHit();
	}
}
