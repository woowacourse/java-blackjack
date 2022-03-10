package blackjack.domain;

public class Dealer extends Gamer {
	public boolean hasHigherScore(Gamer gamer) {
		// return this.score > gamer.score;
		return this.score.isBiggerThan(gamer.score.getScore());
	}

	public boolean hasEqualScore(Gamer gamer) {
		// return this.score == gamer.score;
		return this.score.equals(gamer.score);
	}

	public boolean isHit() {
		// return this.score < 17;
		return this.score.isSmallerThan(17);
	}
}
