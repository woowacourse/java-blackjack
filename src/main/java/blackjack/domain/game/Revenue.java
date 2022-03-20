package blackjack.domain.game;

import blackjack.domain.role.Role;
import java.util.Map;

public class Revenue {

	private final Map<String, Money> revenue;

	public Revenue(final Map<String, Money> revenue) {
		this.revenue = revenue;
	}

	public Money getDealerRevenueResult() {
		Money sumPlayerRevenue = Money.sumOf(revenue.values());
		return new Money(sumPlayerRevenue.getMinusValue());
	}

	public Money getPlayerRevenueResult(Role player) {
		return revenue.get(player.getName());
	}
}