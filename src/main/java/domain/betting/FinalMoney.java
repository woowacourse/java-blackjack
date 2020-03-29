package domain.betting;

import domain.user.Player;

public class FinalMoney {
	private final int money;

	public FinalMoney(int money) {
		this.money = money;
	}

	public int compare(Player player) {
		return player.calculateDifferent(this.money);
	}
}
