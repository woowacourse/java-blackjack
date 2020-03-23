package blackjack.player.domain.report;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import blackjack.player.domain.Money;

public class GameReports {
	private final List<GameReport> gameReports;

	public GameReports(List<GameReport> gameReports) {
		validate(gameReports);
		this.gameReports = gameReports;
	}

	private void validate(List<GameReport> gameReports) {
		if (gameReports == null || gameReports.isEmpty()) {
			throw new IllegalArgumentException("승패정보가 없습니다.");
		}
	}

	public Money calculateDealerProfit() {
		Money gamblerTotalProfit = gameReports.stream()
			.map(GameReport::calculateGamblerProfit)
			.reduce(Money.create(0), Money::sum);

		return gamblerTotalProfit.calculateNegative();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		GameReports that = (GameReports)o;
		return Objects.equals(gameReports, that.gameReports);
	}

	@Override
	public int hashCode() {
		return Objects.hash(gameReports);
	}

	public List<GameReport> getReports() {
		return Collections.unmodifiableList(this.gameReports);
	}
}
