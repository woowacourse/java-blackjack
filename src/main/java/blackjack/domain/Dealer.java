package blackjack.domain;

public class Dealer extends Gamer {
	private static final int HIT_THRESHOLD = 17;

	public Dealer() {
		super(new Name("딜러"));
	}

	private boolean isPlayerAndDealerInNormalCase(Player player) {
		return !this.isBlackJack() && !this.isBust() && !player.isBlackJack() && !player.isBust();
	}

	private boolean isWinByNormalCase(Player player) {
		return isPlayerAndDealerInNormalCase(player) && this.getScore() > player.getScore();
	}

	private boolean isWinBySpecialCase(Player player) {
		if (this.isBlackJack() && !player.isBlackJack()) {
			return true;
		}
		return player.isBust();
	}

	private boolean isDrawBySpecialCase(Player player) {
		return this.isBlackJack() && player.isBlackJack();
	}

	private boolean isDrawByNormalCase(Player player) {
		return isPlayerAndDealerInNormalCase(player)
			&& this.getScore() == player.getScore();
	}

	public boolean isWin(Player player) {
		return isWinByNormalCase(player) || isWinBySpecialCase(player);
	}

	public boolean isDraw(Player player) {
		return isDrawByNormalCase(player) || isDrawBySpecialCase(player);
	}

	public boolean isLose(Player player) {
		return !isWin(player) && !isDraw(player);
	}

	public boolean isHit() {
		return this.getScore() < HIT_THRESHOLD;
	}

	public Card getRandomOneCard() {
		return this.cards.getRandomCard();
	}

	@Override
	public boolean canAddCards() {
		return !this.isBust() && !this.isBlackJack() && this.isHit();
	}
}
