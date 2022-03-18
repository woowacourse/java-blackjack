package blackjack.domain.process;

import blackjack.domain.gamer.Gamer;

public enum Match {
	BLACKJACK_WIN, NOT_BLACKJACK_WIN, DRAW, LOSE, NONE;

	public static Match of(Gamer thisGamer, Gamer otherGamer) {
		if (thisGamer.isDraw(otherGamer)) {
			return DRAW;
		}
		if (thisGamer.isLose(otherGamer)) {
			return LOSE;
		}
		if (thisGamer.isBlackJack()) {
			return BLACKJACK_WIN;
		}
		return NOT_BLACKJACK_WIN;
	}
}
