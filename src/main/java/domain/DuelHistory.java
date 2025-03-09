package domain;

import domain.constant.DuelResult;

public class DuelHistory {
	private int winCount;
	private int loseCount;

	public void write(final DuelResult duelResult) {
		switch (duelResult) {
			case WIN -> winCount++;
			case LOSE -> loseCount++;
		}
	}

	public int getWinCount() {
		return winCount;
	}

	public int getLoseCount() {
		return loseCount;
	}
}
