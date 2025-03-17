package duel;

import java.util.HashMap;
import java.util.Map;

import value.Count;

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

	public DuelResult calculateDuelResult() {
		if (isWin()) {
			return DuelResult.WIN;
		}
		if (isDraw()) {
			return DuelResult.DRAW;
		}
		return DuelResult.LOSE;
	}

	public boolean isWin() {
		return get(DuelResult.WIN).isGreaterThan(get(DuelResult.LOSE))
			&& get(DuelResult.WIN).isGreaterThan(get(DuelResult.DRAW));
	}

	public boolean isDraw() {
		return get(DuelResult.DRAW).isGreaterThan(get(DuelResult.WIN))
			&& get(DuelResult.DRAW).isGreaterThan(get(DuelResult.LOSE));
	}

	private Count get(final DuelResult duelResult) {
		return count.get(duelResult);
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
