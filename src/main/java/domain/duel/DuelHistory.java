package domain.duel;

import domain.card.Count;

public class DuelHistory {
	private Count winCount;
	private Count loseCount;

	public void write(final DuelResult duelResult) {
		switch (duelResult) {
			case WIN -> winCount = winCount.increment();
			case LOSE -> loseCount = loseCount.increment();
		}
	}

	public Count getWinCount() {
		return winCount;
	}

	public Count getLoseCount() {
		return loseCount;
	}

	public boolean isWin() {
		return winCount.isGreaterThan(loseCount);
	}
}
