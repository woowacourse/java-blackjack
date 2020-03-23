package domain.result;

import domain.gamer.Gamer;

public class DrawResult implements ResultPolicy {
	@Override
	public boolean compare(Gamer gamer, Gamer other) {
		return (gamer.isBlackJack() && other.isBlackJack()) ||
			(gamer.isNotBust() && other.isNotBust() && gamer.getScore().isEqualTo(other.getScore()));
	}
}
