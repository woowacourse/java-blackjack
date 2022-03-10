package domain;

public class Dealer extends Gamer {
	public boolean isHigher(Gamer gamer) {
		return this.score > gamer.score;
	}

	public boolean isEqaul(Gamer gamer) {
		return this.score == gamer.score;
	}

	public boolean isHit() {
		return this.score < 17;
	}
}
