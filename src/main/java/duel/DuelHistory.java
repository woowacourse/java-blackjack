package duel;

import java.util.HashMap;
import java.util.Map;

import card.Count;

public class DuelHistory {
	private final Map<DuelResult, Count> count;

	public DuelHistory() {
		count = initDuelHistory();
	}

	private HashMap<DuelResult, Count> initDuelHistory() {
		final HashMap<DuelResult, Count> count = new HashMap<>();
		count.put(DuelResult.WIN, Count.from(0));
		count.put(DuelResult.DRAW, Count.from(0));
		count.put(DuelResult.LOSE, Count.from(0));
		return count;
	}

	public void write(final DuelResult duelResult) {
		count.put(duelResult, count.get(duelResult).increment());
	}

	public boolean isWin() {
		return count.get(DuelResult.WIN).isGreaterThan(count.get(DuelResult.LOSE));
	}

	public Count getWinCount() {
		return count.get(DuelResult.WIN);
	}

	public Count getLoseCount() {
		return count.get(DuelResult.LOSE);
	}

	public Count getDrawCount() {
		return count.get(DuelResult.DRAW);
	}
}
