package blackjack.domain;

public class Dealer extends Gamer {
	public Dealer() {
		super(new Name("딜러"));
	}

	public boolean isHigher(Gamer gamer) {
		return this.getScore() > gamer.getScore();
	}

	public boolean isEqual(Gamer gamer) {
		return this.getScore() == gamer.getScore();
	}

	public boolean isHit() {
		return this.getScore() < 17;
	}

}
