package blakcjack.domain;

import java.util.Objects;

public class Outcome {
	private final int winCount;
	private final int drawCount;
	private final int loseCount;

	public Outcome(final int winCount, final int drawCount, final int loseCount) {
		this.winCount = winCount;
		this.drawCount = drawCount;
		this.loseCount = loseCount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Outcome outcome = (Outcome) o;
		return winCount == outcome.winCount && drawCount == outcome.drawCount && loseCount == outcome.loseCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(winCount, drawCount, loseCount);
	}
}
