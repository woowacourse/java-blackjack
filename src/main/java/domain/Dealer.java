package domain;

public class Dealer extends Gamer {
	public boolean isHigher(Gamer gamer) {
		return this.score > gamer.score;
	}
}
