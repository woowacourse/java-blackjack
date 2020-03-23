package domain.gamer;

import domain.result.MatchResult;

public class Player extends Gamer {
	private static final int DRAW_CARD_PIVOT = 21;

	public Player(String name, String money) {
		super(name, money);
	}

	public MatchResult findMatchResult(Dealer dealer) {
		if (isBlackJack() && dealer.isBlackJack()) {
			return MatchResult.DRAW;
		}

		if (isBlackJack()) {
			return MatchResult.BLACKJACK;
		}

		if (dealer.isBlackJack()) {
			return MatchResult.LOSE;
		}

		return MatchResult.of(calculateScore(), dealer.calculateScore());
	}

	@Override
	public boolean isDrawable() {
		return super.calculateScore() < DRAW_CARD_PIVOT;
	}
}
