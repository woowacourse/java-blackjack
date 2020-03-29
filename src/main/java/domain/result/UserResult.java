package domain.result;

import java.util.Objects;

public class UserResult {
	private final String name;
	private final int profit;

	public UserResult(String name, int profit) {
		this.name = Objects.requireNonNull(name);
		this.profit = profit;
	}

	public String getName() {
		return name;
	}

	public int getProfit() {
		return profit;
	}
}
