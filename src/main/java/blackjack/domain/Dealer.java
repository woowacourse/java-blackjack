package blackjack.domain;

public class Dealer extends Gamer {
	private static final int HIT_THRESHOLD = 17;

	public Dealer() {
		super(new Name("딜러"));
	}

	public boolean isWin(Gamer gamer) {
		return (this.getScore() > gamer.getScore()) || (this.isBlackJack() && !gamer.isBlackJack());
	}

	public boolean isDraw(Gamer gamer) {
		return this.getScore() == gamer.getScore() || (this.isBlackJack() && gamer.isBlackJack());
	}

	public boolean isLose(Gamer gamer) {
		return this.isBust() || this.getScore() < gamer.getScore() || (!this.isBlackJack() && gamer.isBlackJack());
	}

	public boolean isHit() {
		return this.getScore() < HIT_THRESHOLD;
	}

	public Card getRandomOneCard() {
		return this.cards.getRandomCard();
	}
}
