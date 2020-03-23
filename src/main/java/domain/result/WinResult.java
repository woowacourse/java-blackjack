package domain.result;

import domain.gamer.Gamer;

public class WinResult implements ResultPolicy {
	@Override
	public boolean canApply(Gamer gamer, Gamer other) {
		return gamer.isNotBlackJack() && gamer.isNotBust() &&
			(other.isBust() || gamer.getScore().isBiggerThan(other.getScore()));
	}
}
