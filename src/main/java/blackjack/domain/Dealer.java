package blackjack.domain;

public class Dealer extends Gamer {
	private static final int HIT_THRESHOLD = 17;

	public Dealer() {
		super(new Cards(), new Name("딜러"));
	}

	@Override
	protected boolean isWinBySpecialCase(Gamer gamer) {
		if (this.isBlackJack() && !gamer.isBlackJack()) {
			return true;
		}
		return gamer.isBust();
	}

	public Card getRandomOneCard() {
		return this.cards.getRandomCard();
	}

	@Override
	public boolean canHit() {
		return this.getScore() < HIT_THRESHOLD;
	}
}
