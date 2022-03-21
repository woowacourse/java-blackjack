package blackjack.domain.betting;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.process.Match;

public class Profit {
	private final int profitMoney;

	private Profit(int profitMoney) {
		this.profitMoney = profitMoney;
	}

    public static Profit from(int profitMoney) {
        return new Profit(profitMoney);
    }

    public int getProfitMoney() {
        return profitMoney;
    }
}
