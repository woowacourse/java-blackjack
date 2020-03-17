package domain.gamer;

import exception.NameFormatException;

public class Player extends Gamer {
	private static final String PATTERN = "[a-zA-Z가-힣]*";
	private static final int DRAW_CARD_PIVOT = 21;

	public Player(String name, String money) {
		super(name, money);
		if (isInvalidName(name)) {
			throw new NameFormatException("잘못된 이름입니다.");
		}
	}

	private boolean isInvalidName(String name) {
		return !name.matches(PATTERN);
	}

	public MatchResult findMatchResult(int dealerScore) {
		if (calculateScore() > DRAW_CARD_PIVOT) {
			return MatchResult.LOSE;
		}

		if (dealerScore > DRAW_CARD_PIVOT) {
			return MatchResult.WIN;
		}

		return MatchResult.of(calculateScore() - dealerScore);
	}

	@Override
	public boolean isDrawable() {
		return super.calculateScore() < DRAW_CARD_PIVOT;
	}
}
