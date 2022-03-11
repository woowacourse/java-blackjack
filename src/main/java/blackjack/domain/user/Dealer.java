package blackjack.domain.user;

public class Dealer extends Gamer {
	public boolean hasHigherScore(Gamer gamer) {
		return this.cards.hasHigherScore(gamer.cards);
	}

	public boolean hasEqualScore(Gamer gamer) {
		return this.cards.isEqualScoreWith(gamer.cards);
	}

	public boolean isHit() {
		return this.cards.isHit();
	}
}
