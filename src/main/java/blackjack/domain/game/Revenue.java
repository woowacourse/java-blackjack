package blackjack.domain.game;

import blackjack.domain.role.Role;
import java.util.Map;

public class Revenue {

	private final Map<Role, Money> revenue;

	public Revenue(final Map<Role, Money> revenue) {
		this.revenue = revenue;
	}

	public Money getDealerRevenueResult() {
		Money sumPlayerRevenue = Money.sumOf(revenue.values());
		return sumPlayerRevenue.getMinusValue();
	}

	public Money getPlayerRevenueResult(Role player) {
		return revenue.get(player);
	}
}